package com.materials.web.util;

import com.materials.web.model.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by arturk on 11.06.2016.
 */
public class SearchUtil {

    public List<Document> searchDocumentList(
            String libraryKey, String name, String department, String publishingYear, String authorSurname) {

        List<Document> byLibraryKey = new ArrayList<>();
        List<Document> byName = new ArrayList<>();
        List<Document> byDepartment = new  ArrayList<>();
        List<Document> byPublishingYear = new ArrayList<>();
        List<Document> byAuthorSurname = new ArrayList<>();

        if(!Objects.equals(libraryKey, "")) {
            byLibraryKey = result(libraryKey);
        }
        if(Objects.equals(name, "")) {
            byName = result(name);
        }
        if(Objects.equals(department, "")) {
            byDepartment = result(department);
        }
        if(Objects.equals(publishingYear, "")) {
            byDepartment = result(publishingYear);
        }
        if(Objects.equals(authorSurname, "")) {
            byDepartment = result(authorSurname);
        }

        List<List<Document>> resultListOfDocumentList = new ArrayList<>();

        List<List<Document>> listOfDocumentList = new ArrayList<>(
                Arrays.asList(byLibraryKey, byName, byDepartment, byPublishingYear, byAuthorSurname));

        resultListOfDocumentList.addAll(listOfDocumentList.stream().filter(documentList -> !documentList.isEmpty()).collect(Collectors.toList()));
        return getResultList(resultListOfDocumentList);
    }

    private List<Document> getResultList(List<List<Document>> listList) {
        List<Document> resultList = new ArrayList<>();

        for (int i = 0; i < listList.size() - 1; i++) {
            if(!resultList.isEmpty() || i == 0 ) {
                if (resultList.isEmpty()) {
                    resultList = getEqualsObjects(listList.get(i), listList.get(i + 1));
                } else {
                    resultList = getEqualsObjects(resultList, listList.get(i + 1));
                }
            } else {
                return new ArrayList<>();
            }
        }
        return resultList;
    }

    private List<Document> getEqualsObjects(List<Document> firstList, List<Document> secondList) {
        return firstList.stream().filter(secondList::contains).collect(Collectors.toList());
    }

    private List<Document> result(String query) {
        return  new ArrayList<>();
    }
}
