package com.example.webfactorydemo.models;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full Name is mandatory")
    private String fullName;

    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6)
    private String password;


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
