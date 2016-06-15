package com.materials.web.model;

import com.materials.web.model.enumeration.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by arturk on 19.05.2016.
 */

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "library_key")
    private Integer libraryKey;

    @Column(name = "name")
    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "publishing_year")
    private Short publishingYear;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Column(name = "upload_date")
    private Timestamp uploadDate;

    @NotNull
    @Column(name = "object_key")
    private String objectKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "document_author", joinColumns = {
            @JoinColumn(name = "document_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id",
                            nullable = false)})
    @OrderBy("surname")
    private Set<Author> authorSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "document_specialty", joinColumns = {
            @JoinColumn(name = "document_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "specialty_id",
                            nullable = false)})
    @OrderBy("name")
    private Set<Specialty> specialtySet = new HashSet<>();

    public Document() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLibraryKey() {
        return libraryKey;
    }

    public void setLibraryKey(Integer libraryKey) {
        this.libraryKey = libraryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Short getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(Short publishingYear) {
        this.publishingYear = publishingYear;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Set<Author> getAuthorSet() {
        return authorSet;
    }

    public void setAuthorSet(Set<Author> authorSet) {
        this.authorSet = authorSet;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Specialty> getSpecialtySet() {
        return specialtySet;
    }

    public void setSpecialtySet(Set<Specialty> specialtySet) {
        this.specialtySet = specialtySet;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Document d = (Document) obj;
        return Objects.equals(this.id, d.id);
    }
}
