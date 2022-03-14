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
    private String username;



    @OneToMany(mappedBy = "op")
    private List<Publication> publications;


    public User(String username){
        this.username = username;
    }

    public User() {

    }
}
