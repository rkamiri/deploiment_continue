package com.example.cc1romainkamiri.domain.entity;

import java.util.Objects;

public class User {

    private Id id;

    private final String firstname;
    private final String lastname;
    private final String password;
    private final String email;

    public User(Id id, String firstname, String lastname, String password, String email) {
        this.id = id;
        this.firstname = Objects.requireNonNull(firstname);
        this.lastname = Objects.requireNonNull(lastname);
        this.password = Objects.requireNonNull(password);
        this.email = Objects.requireNonNull(email);
    }

    public static User of(Id id, String firstname, String lastname, String password, String email){
        return new User(id, firstname, lastname, password, email);
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
