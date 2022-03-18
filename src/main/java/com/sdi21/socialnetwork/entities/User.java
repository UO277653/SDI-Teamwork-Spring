package com.sdi21.socialnetwork.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String email;


    private String name;
    private String surname;
    private String role;

    private String password;
    @Transient
    private String passwordConfirm;

    @OneToMany(mappedBy = "op")
    private List<Publication> publications;

    public User() {}

    public User(String email){
        this.email = email;
    }

    public User(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}'; 
    }


}
