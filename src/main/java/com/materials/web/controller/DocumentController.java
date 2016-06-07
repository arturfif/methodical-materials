package com.materials.web.controller;

import com.materials.web.dao.inter.*;
import com.materials.web.dto.DocumentDto;
import com.materials.web.model.Faculty;
import com.materials.web.model.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "document/")
public class DocumentController {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private SpecialtyDAO specialtyDAO;
    private FacultyDAO facultyDAO;
    private StudentDAO studentDAO;
    private List<Faculty> facultyList;
    private List<Specialty> specialtyList;

    @Autowired
    public DocumentController(UserDAO userDAO, RoleDAO roleDAO, SpecialtyDAO specialtyDAO, FacultyDAO facultyDAO,
                          StudentDAO studentDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.specialtyDAO = specialtyDAO;
        this.facultyDAO = facultyDAO;
        this.studentDAO = studentDAO;
        facultyList = facultyDAO.list();
        specialtyList = specialtyDAO.list();
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addFile(Model model) {
        model.addAttribute("documentDto", new DocumentDto());
        return "add-file";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String saveFile(@Valid @ModelAttribute("documentDto") DocumentDto documentDto,
                           BindingResult bindingResult, Model model) {

        model.addAttribute("user", "Юзер");
        MultipartFile documentPath = documentDto.getDocumentPath();

        boolean fail = false;
        if (bindingResult.hasErrors()) {
            fail = true;
        }
        if(fail) {
            return "add-file";
        }
        return "add-file";
    }

}
