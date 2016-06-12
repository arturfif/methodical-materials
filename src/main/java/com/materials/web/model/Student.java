package com.materials.web.model;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty studentSpecialty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Specialty getStudentSpecialty() {
        return studentSpecialty;
    }

    public void setStudentSpecialty(Specialty studentSpecialty) {
        this.studentSpecialty = studentSpecialty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userOfStudent) {
        this.user = userOfStudent;
    }

    public Student() {

    }
}
