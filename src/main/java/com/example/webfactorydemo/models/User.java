package com.example.webfactorydemo.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @OneToMany
    private Set<Posts> posts;

    public User() {
    }

    public User(String fullName, String email, String password) {

        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
