package com.materials.web.util.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.script.Script;
import com.google.api.services.script.model.ExecutionRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class GoogleScriptUtil {
    /** Application name. */
    private static final String APPLICATION_NAME =
            "Drive folder to zip app";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/script-java-quickstart.json");
    private static final String FUNCTION_NAME = "myFunction";

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
     * at ~/.credentials/script-java-quickstart.json
     */
    private static final List<String> SCOPES =
            Collections.singletonList("https://www.googleapis.com/auth/drive");

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    private static final String SCRIPT_ID = "MTAPtJQM5u03x9A8SasP70Tl1d_FRDeJB";

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    private static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                new FileInputStream("D:\\DIIT\\_Year_4\\term2\\diplom7\\spring-security-login-form-database\\client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        return new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
    }


    private static HttpRequestInitializer setHttpTimeout(
            final HttpRequestInitializer requestInitializer) {
        return httpRequest -> {
            requestInitializer.initialize(httpRequest);
            // This allows the API to call (and avoid timing out on)
            // functions that take up to 6 minutes to complete (the maximum
            // allowed script run time), plus a little overhead.
            httpRequest.setReadTimeout(380000);
        };
    }


    private static Script getScriptService() throws IOException {
        Credential credential = authorize();
        return new Script.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, setHttpTimeout(credential))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void createZipFromMaterialsFolder() throws IOException {
        Script scriptService = getScriptService();
        scriptService.scripts().run(SCRIPT_ID, new ExecutionRequest().setFunction(FUNCTION_NAME)).execute();
    }

    public static void main(String[] args) {
        try {
            createZipFromMaterialsFolder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
