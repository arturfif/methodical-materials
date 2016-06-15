package com.materials.web.controller;

import com.materials.web.util.google.GoogleDriveUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DownloadController {

    private static String partLink = "https://drive.google.com/open?id=";

    @RequestMapping(value = "/download")
    public String downloadMaterials(Model model) {
        String zipFileObjectKey;
        try {
            zipFileObjectKey = GoogleDriveUtil.getZipFileObjectKey();
        } catch (Exception e) {
            model.addAttribute("error", "Не удалось скачать базу материалов!");
            return "public/index";
        }
        return "redirect:" + partLink + zipFileObjectKey;
    }

}
