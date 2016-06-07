package com.materials.web.dto;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class DocumentDto {

    @Pattern(regexp = "^[0-9]{1,6}$")
    private String libraryKey;

    @Pattern(regexp = "^[A-Za-zа-яА-Я- ]{1,255}$")
    private String name;

    @NotNull
    private Long departmentId;

    @NotNull
    private Short publishingYear;

    @NotNull
    private List<Long> specialtyList;

    @NotNull
    private List<String> authorList;

    @NotNull
    private MultipartFile documentPath;

    public DocumentDto() {
    }

    public String getLibraryKey() {
        return libraryKey;
    }

    public void setLibraryKey(String libraryKey) {
        this.libraryKey = libraryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Short getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Short publishingYear) {
        this.publishingYear = publishingYear;
    }

    public List<Long> getSpecialtyList() {
        return specialtyList;
    }

    public void setSpecialtyList(List<Long> specialtyList) {
        this.specialtyList = specialtyList;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    public MultipartFile  getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(MultipartFile documentPath) {
        this.documentPath = documentPath;
    }
}
