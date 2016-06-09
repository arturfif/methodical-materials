package com.materials.web.controller;

import com.materials.web.dao.inter.*;
import com.materials.web.dto.DocumentDto;
import com.materials.web.model.*;
import com.materials.web.model.enumeration.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "document/")
public class DocumentController {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private SpecialtyDAO specialtyDAO;
    private FacultyDAO facultyDAO;
    private StudentDAO studentDAO;
    private DepartmentDAO departmentDAO;
    private DocumentDAO documentDAO;
    private AuthorDAO authorDAO;
    private List<Faculty> facultyList;
    private List<Specialty> specialtyList;
    private List<Department> departmentList;

    @Autowired
    public DocumentController(UserDAO userDAO, RoleDAO roleDAO, SpecialtyDAO specialtyDAO, FacultyDAO facultyDAO,
                          StudentDAO studentDAO, DepartmentDAO departmentDAO, DocumentDAO documentDAO, AuthorDAO authorDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.specialtyDAO = specialtyDAO;
        this.facultyDAO = facultyDAO;
        this.studentDAO = studentDAO;
        this.departmentDAO = departmentDAO;
        this.documentDAO = documentDAO;
        this.authorDAO = authorDAO;
        facultyList = facultyDAO.list();
        specialtyList = specialtyDAO.list();
        departmentList = departmentDAO.list();
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addFile(Model model) {
        model.addAttribute("documentDto", new DocumentDto());
        model.addAttribute("facultyList", facultyList);
        model.addAttribute("specialtyList", specialtyList);
        model.addAttribute("departmentList", departmentList);
        return "add-file";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String saveFile(@Valid @ModelAttribute("documentDto") DocumentDto documentDto,
                           BindingResult bindingResult, Model model,
                           HttpServletRequest request) {

        if(request.isUserInRole("ADMIN")) {
            documentDto.setStatus(Status.CHECKED.name());
        } else {
            documentDto.setStatus(Status.UNCHECKED.name());
        }

        String username = request.getUserPrincipal().getName();
        documentDto.setUser(userDAO.get(username));

        MultipartFile documentDtoFile = documentDto.getFile();
        //load file get object key

        documentDto.setObjectKey("@#J(*hd389ch23");

        Document document = documentDto.buildDocument();
        document.setSpecialtySet(toSpecialtySet(documentDto.getSpecialtySet()));
        document.setAuthorSet(toAuthorSet(documentDto.getAuthorSet()));
        document.setDepartment(toDepartment(documentDto.getDepartmentId()));
        documentDAO.save(document);

        // TODO

        boolean fail = false;
        if (bindingResult.hasErrors()) {
            fail = true;
        }
        if(fail) {
            model.addAttribute("error", "При загрузке файла произошла ошибка!");
            return "add-file";
        }
        model.asMap().clear();
        model.addAttribute("success", "Документ успешно добавлен в систему!");
        return "add-file";
    }

    @RequestMapping(value = "my", method = RequestMethod.GET)
    public String myDocuments(Model model, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        List<Document> documentList = new ArrayList<>();
        documentList.addAll(userDAO.get(username).getDocumentSet());
        model.addAttribute("documentList", documentList);
        return "my-document";
    }

    @RequestMapping(value = "check", method = RequestMethod.GET)
    public String checkDocuments(Model model) {
        model.addAttribute("documentList", documentDAO.listUnchecked());
        return "check-document";
    }



    private Set<Specialty> toSpecialtySet(Set<Long> longSet) {
        Set<Specialty> specialtySet = new HashSet<>();
        for (Long aLong : longSet) {
            specialtySet.add(specialtyDAO.get(aLong));
        }
        return specialtySet;
    }

    private Department toDepartment(Long id) {
        return departmentDAO.get(id);
    }

    private Set<Author> toAuthorSet(Set<String> stringSet) {
        Set<Author> authorSet = new HashSet<>();
        for (String string : stringSet) {
            authorSet.add(toAuthor(string));
        }
        return authorSet;
    }


    private Author toAuthor(String string) {

        Author author = new Author();

        String trim = string.trim();
        int spaceIndex = trim.indexOf(" ");
        String surname = trim.substring(0, spaceIndex);
        String name = trim.substring(spaceIndex + 1, spaceIndex + 3);
        String patronymic;

        if(trim.length() > spaceIndex + 4) {
            patronymic = trim.substring(spaceIndex + 3, spaceIndex + 5);

        } else {
            patronymic = "";
        }

        author.setSurname(surname);
        author.setName(String.valueOf(name));
        author.setPatronymic(patronymic);

        Author authorFromDB = authorDAO.get(author);

        if(authorFromDB != null) {
            return authorFromDB;
        }
        return author;
    }

}
