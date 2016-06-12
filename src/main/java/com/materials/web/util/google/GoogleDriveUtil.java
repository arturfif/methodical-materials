package com.materials.web.util.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public class GoogleDriveUtil {



    /** Application name. */
    private static final String APPLICATION_NAME =
            "Drive API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials");

    private static final String GOOGLE_MATERIALS_FOLDER = "0B2jorNLu7tz-N1dMOHJWOTNUc00";

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart.json
     */
    private static final List<String> SCOPES =
            Collections.singletonList(DriveScopes.DRIVE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }


    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                new FileInputStream("D:\\DIIT\\_Year_4\\term2\\diplom7\\springMVC1805\\client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Drive client service.
     * @return an authorized Drive client service
     * @throws IOException
     */
    private static Drive getDriveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static String uploadFile(java.io.File file) throws IOException {
        Drive service = getDriveService();
        File googleFile = new File();
        googleFile.setName(file.getName());
        googleFile.setParents(Collections.singletonList(GOOGLE_MATERIALS_FOLDER));

        FileContent content = new FileContent(Files.probeContentType(file.toPath()), file);

        String objectId = service.files().create(googleFile, content).execute().getId();

        Permission permission = new Permission();
        permission.setRole("reader").setType("anyone");
        service.permissions().create(objectId, permission).execute();

        return objectId;
    }

    public static void deleteFile(String objectKey) throws IOException {
        Drive service = getDriveService();
        service.files().delete(objectKey).execute();
    }

    public static void createFolder() throws IOException {
        Drive service = getDriveService();
        File fileMetadata = new File();
        fileMetadata.setName("methodical-materials");
        fileMetadata.setMimeType("application/vnd.google-apps.folder");

        File file = service.files().create(fileMetadata)
                .setFields("id")
                .execute();
        System.out.println("Folder ID: " + file.getId());
    }

    public static void createFileInZip() throws IOException {
        Drive service = getDriveService();
        String folderId = "0B2jorNLu7tz-RXhzekZ1WlVFdHM";
        File fileMetadata = new File();
        fileMetadata.setName("photo.jpg");
        fileMetadata.setParents(Collections.singletonList(folderId));
        java.io.File filePath = new java.io.File("C:\\Users\\arturk\\registration.jpg");
        FileContent mediaContent = new FileContent("image/jpeg", filePath);
        File file = service.files().create(fileMetadata, mediaContent)
                .setFields("id, parents")
                .execute();
        System.out.println("File ID: " + file.getId());
    }


    public static String getZipFileObjectKey() throws IOException {
        Drive service = getDriveService();
        List<File> fileList = service.files().list()
                .setQ("mimeType='application/zip'")
                .setSpaces("drive")
                .setOrderBy("createdTime")
                .setFields("nextPageToken, files(id)")
                .setPageToken(null)
                .execute().getFiles();
        return  fileList.get(0).getId();
    }

    public static void deleteZipFile() throws IOException {
        Drive service = getDriveService();
        String removeId = getZipFileObjectKey();
        service.files().delete(removeId).execute();
    }

    public static void main(String[] args) {
        try {
            String zipFileObjectKey = getZipFileObjectKey();
            System.out.println(zipFileObjectKey);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
