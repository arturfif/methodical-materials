package com.materials.web.controller;

import com.materials.web.dao.inter.*;
import com.materials.web.dto.StudentDto;
import com.materials.web.dto.UserDto;
import com.materials.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "admin/")
public class UserController {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private SpecialtyDAO specialtyDAO;
    private FacultyDAO facultyDAO;
    private StudentDAO studentDAO;
    private List<Faculty> facultyList;
    private List<Specialty> specialtyList;

    @Autowired
    public UserController(UserDAO userDAO, RoleDAO roleDAO, SpecialtyDAO specialtyDAO, FacultyDAO facultyDAO,
                          StudentDAO studentDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.specialtyDAO = specialtyDAO;
        this.facultyDAO = facultyDAO;
        this.studentDAO = studentDAO;
        facultyList = facultyDAO.list();
        specialtyList = specialtyDAO.list();
    }

    @RequestMapping(value = "account/admin/add", method = RequestMethod.GET)
    public String addAdmin(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "add-admin";
    }

    @RequestMapping(value = "account/admin/add", method = RequestMethod.POST)
    public String saveAdmin(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model) {

        boolean fail = false;
        if(userDAO.get(userDto.getUsername()) != null) {
            fail = true;
            model.addAttribute("usernameError", "Этот логин занят");
        }
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            fail = true;
            model.addAttribute("passwordError", "Пароли не совпадают");
        }
        if (bindingResult.hasErrors()) {
            fail = true;
        }
        if(fail) {
            return "add-admin";
        }

        Role role = roleDAO.get(userDto.getRoleId());
        User user = new User(userDto);
        user.setRole(role);

        userDAO.save(user);
        model.addAttribute("success", "Пользователь " + userDto.getUsername() + " успешно зарегистрирован!");
        return "add-admin";
    }

    @RequestMapping(value = "account/student/add", method = RequestMethod.GET)
    public String addStudent(Model model) {


        model.addAttribute("facultyList", facultyList);
        model.addAttribute("specialtyList", specialtyList);

        model.addAttribute("studentDto", new StudentDto());
        return "add-student";
    }

    @RequestMapping(value = "account/student/add", method = RequestMethod.POST)
    public String saveStudent(@Valid @ModelAttribute("studentDto") StudentDto studentDto, BindingResult bindingResult, Model model) {

        model.addAttribute("specialtyList", specialtyList);
        boolean fail = false;
        if(userDAO.get(studentDto.getUsername()) != null) {
            fail = true;
            model.addAttribute("usernameError", "Этот логин занят");
        }
        if (!studentDto.getPassword().equals(studentDto.getConfirmPassword())) {
            fail = true;
            model.addAttribute("passwordError", "Пароли не совпадают");
        }
        if (bindingResult.hasErrors()) {
            fail = true;
        }
        if(fail) {
            return "add-student";
        }

        Role role = roleDAO.get(3);
        User user = new User(studentDto);
        user.setRole(role);
        userDAO.save(user);
        Student student = new Student();
        student.setStudentSpecialty(specialtyDAO.get(studentDto.getSpecialtyId()));
        student.setUserOfStudent(user);
        studentDAO.save(student);

        model.addAttribute("success", "Пользователь " + studentDto.getUsername() + " успешно зарегистрирован!");
        model.addAttribute("specialtyList", specialtyList);
        return "add-student";
    }

}
