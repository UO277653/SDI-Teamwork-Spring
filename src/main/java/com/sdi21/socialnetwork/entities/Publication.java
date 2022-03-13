package com.sdi21.socialnetwork.entities;


import javax.persistence.*;

@Entity
@Table(name = "publication")
public class Publication {

    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String text;

    @ManyToOne
    private User op;


}
