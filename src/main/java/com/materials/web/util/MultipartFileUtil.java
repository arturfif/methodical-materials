package com.materials.web.util;


import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultipartFileUtil {
    public static File  multipartToFile(MultipartFile multipartFile, String filename) throws IOException {
        String extension = getExtension(multipartFile.getOriginalFilename());
        File file = new File(System.getProperty("user.home") + "\\" + filename + extension);
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(file));
        FileCopyUtils.copy(multipartFile.getInputStream(), stream);
        stream.close();
        return file;
    }

    private static String getExtension(String filename) {
        int extensionDot = filename.lastIndexOf(".");
        return filename.substring(extensionDot);
    }
}
