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

    public Publication(String title, String text){

        this.date = new Date();
        this.title = title;
        this.text = text;

    }

    public Publication() {

    }


    public void setOp(User user){
        this.op = user;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public User getOp() {
        return op;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", op=" + op +
                '}';
    }
}
