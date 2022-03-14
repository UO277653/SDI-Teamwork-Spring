package com.sdi21.socialnetwork.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "publication")
public class Publication {

    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String text;
    private Date date;

    @ManyToOne
    private User op;


}
