package com.materials.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String viewSearch(Model model, @RequestParam(value = "searchQuery", required = false) String searchQuery) {
        if(searchQuery == null || searchQuery.equals("")) {
            return "view-search";
        } else {
            //search
            //List<DocumentDto>
            return "search";
        }
    }

}
