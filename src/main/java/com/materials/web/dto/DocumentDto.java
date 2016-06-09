package com.materials.web.dto;


import com.materials.web.model.Document;
import com.materials.web.model.User;
import com.materials.web.model.enumeration.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

public class DocumentDto {

    @Pattern(regexp = "^[0-9]{0,6}$")
    private String libraryKey;

    @Pattern(regexp = "^[A-Za-zа-яА-Я- ]{1,255}$")
    private String name;

    @NotNull
    private Long departmentId;

    @NotNull
    private Short publishingYear;

    @NotNull
    private Set<Long> specialtySet;

    @NotNull
    private Set<String> authorSet;

    @NotNull
    private MultipartFile file;

    private String status;

    private String objectKey;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DocumentDto() {
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Set<Long> getSpecialtySet() {
        return specialtySet;
    }

    public void setSpecialtySet(Set<Long> specialtySet) {
        this.specialtySet = specialtySet;
    }

    public Set<String> getAuthorSet() {
        return authorSet;
    }

    public void setAuthorSet(Set<String> authorSet) {
        this.authorSet = authorSet;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Document buildDocument() {
        Document document = new Document();
        document.setLibraryKey(toLibraryKey(this.libraryKey));
        document.setName(this.name);
        document.setPublishingYear(this.publishingYear);
        document.setObjectKey(this.objectKey);
        document.setStatus(toStatus(this.status));
        document.setUser(this.getUser());
        document.setUploadDate(new Timestamp(new Date().getTime()));
        return document;
    }

    private Status toStatus(String string) {
        return Status.valueOf(string);
    }

    private Integer toLibraryKey(String string) {
        Integer integer = null;
        if(string != null && string.trim().length() != 0) {
            integer = Integer.parseInt(string);
        }
        return integer;
    }


}
