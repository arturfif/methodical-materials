package com.materials.web.controller;

import com.materials.web.dao.inter.DocumentDAO;
import com.materials.web.dao.inter.SpecialtyDAO;
import com.materials.web.dao.inter.UserDAO;
import com.materials.web.model.Document;
import com.materials.web.model.User;
import com.materials.web.model.enumeration.RoleEnum;
import com.materials.web.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class SearchController {

    private DocumentDAO documentDAO;
    private UserDAO userDAO;
    private SpecialtyDAO specialtyDAO;

    @Autowired
    public SearchController(DocumentDAO documentDAO, UserDAO userDAO, SpecialtyDAO specialtyDAO) {
        this.documentDAO = documentDAO;
        this.userDAO = userDAO;
        this.specialtyDAO = specialtyDAO;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String viewSearch(Model model,
                             @RequestParam(value = "searchQuery", required = false) String searchQuery,
                             HttpServletRequest request) {

        model.addAttribute("searchTitle", "Результаты поиска:");
        if (searchQuery == null || searchQuery.trim().equals("")) {
            String username = request.getUserPrincipal().getName();
            User user = userDAO.get(username);
            if (user.getRole().getRoleEnum() == RoleEnum.STUDENT) {
                Long specialtyId = user.getStudent().getStudentSpecialty().getId();
                List<Document> documentList = specialtyDAO.get(specialtyId).getCheckedDocumentList();
                model.addAttribute("documentList", documentList);
                model.addAttribute("searchTitle", "Результаты поиска по Вашей специальности:");
                return "public/search";
            }

            return "public/view-search";
        } else {
            searchQuery = searchQuery.trim();
            List<Document> documentList = search(searchQuery);
            model.addAttribute("searchQuery", searchQuery);

            model.addAttribute("documentList", documentList);
            return "public/search";
        }
    }

    private List<Document> search(String searchQuery) {
        List<String> stringList = new ArrayList<>();
        stringList.addAll(Arrays.asList(searchQuery.split(" ")));

        List<Document> resultList = new ArrayList<>();

        for (String keyWord : stringList) {
            if (org.apache.commons.lang.math.NumberUtils.isNumber(keyWord)) {
                List<Document> documentList = documentDAO.listByLibraryKey(Integer.parseInt(keyWord));
                resultList.addAll(documentList);
                List<Document> documentList1 = documentDAO.listByPublishingYear(Short.parseShort(keyWord));
                resultList.addAll(documentList1);
            }
            resultList.addAll(documentDAO.listByName(keyWord));
            resultList.addAll(documentDAO.listByAuthorSurname(keyWord));
            resultList.addAll(documentDAO.listByDepartment(keyWord));
        }
        resultList = priorityList(resultList);
        return resultList;
    }

    private List<Document> priorityList(List<Document> documentList) {

        Map<Document, Integer> frequencyMap = new HashMap<>();
        for (Document document : documentList) {
            int frequency = Collections.frequency(documentList, document);
            if(!(Collections.frequency(frequencyMap.keySet(), document) > 0)) {
                frequencyMap.put(document, frequency);
            }
        }
        return MapUtil.entriesSortedByValues(frequencyMap);
    }

}
