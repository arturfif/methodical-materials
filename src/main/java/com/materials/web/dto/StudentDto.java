package com.materials.web.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StudentDto {


    @NotBlank
    @Pattern(regexp = "^[а-яА-Я- ]{0,50}$")
    private String surname;

    @NotBlank
    @Pattern(regexp = "^[а-яА-Я- ]{0,50}$")
    private String name;

    @Pattern(regexp = "^[а-яА-Я- ]{0,50}$")
    private String patronymic;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{3,50}$")
    private String username;

    @Pattern(regexp = "^.{6,20}$")
    private String password;

    @Pattern(regexp = "^.{6,20}$")
    private String confirmPassword;

    @NotNull
    private Long specialtyId;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

}
