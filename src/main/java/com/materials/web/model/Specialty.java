package com.materials.web.model;

import com.materials.web.model.enumeration.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "specialty")
public class Specialty {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "studentSpecialty")
    private Set<Student> studentSet = new HashSet<>();

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "specialtySet")
    @OrderBy("uploadDate DESC")
    private Set<Document> documentSet = new HashSet<>();

    public Specialty() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Document> getDocumentSet() {
        return documentSet;
    }

    public List<Document> getCheckedDocumentList() {
        List<Document> documentList = this.documentSet.stream().filter(document -> document.getStatus() == Status.CHECKED).collect(Collectors.toList());
        Collections.sort(documentList, (o1, o2) -> o2.getUploadDate().compareTo(o1.getUploadDate()));
        return documentList;
    }

    public void setDocumentSet(Set<Document> documentSet) {
        this.documentSet = documentSet;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }
}
