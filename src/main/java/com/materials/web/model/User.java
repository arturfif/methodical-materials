package com.materials.web.model;

import com.materials.web.dto.StudentDto;
import com.materials.web.dto.UserDto;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

/**
 * Created by arturk on 19.05.2016.
 */

@Entity
@Table(name = "\"user\"")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9-]{3,50}$")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank
    @Pattern(regexp = "^.{6,20}$")
    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    @NotNull
    private Role role;

    @OneToMany(mappedBy = "userOfDocument", fetch = FetchType.EAGER)
    private Set<Document> documentSet = new HashSet<>();

    @OneToMany(mappedBy = "userOfStudent")
    private Set<Student> studentSet = new HashSet<>();

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public User() {
    }

    public User(UserDto userDto) {
        this.name = userDto.getName();
        this.surname = userDto.getSurname();
        this.patronymic = userDto.getPatronymic();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
    }

    public User(StudentDto studentDto) {
        this.name = studentDto.getName();
        this.surname = studentDto.getSurname();
        this.patronymic = studentDto.getPatronymic();
        this.username = studentDto.getUsername();
        this.password = studentDto.getPassword();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(role.getRoleEnum().name()));
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Document> getDocumentSet() {
        return documentSet;
    }

    public void setDocumentSet(Set<Document> documentSet) {
        this.documentSet = documentSet;
    }
}
