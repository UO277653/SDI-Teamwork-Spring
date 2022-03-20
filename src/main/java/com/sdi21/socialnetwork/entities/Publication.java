package com.sdi21.socialnetwork.entities;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "publication")
public class Publication {

    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String text;
    private Date date;
    private String state = "Aceptada";

    @ManyToOne
    private User op;


    // Tabla que marca la relación "recommends", n-n entre usuarios y publicaciones
    @JoinTable(
        name = "rel_user_recommends",
        joinColumns = @JoinColumn(name = "FK_PUBLICATION", nullable = false),
        inverseJoinColumns = @JoinColumn(name="FK_USER", nullable = false)
    )
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<User> recommendations;


    public Publication(String title, String text){
        this.date = new Date();
        this.title = title;
        this.text = text;
        this.recommendations = new HashSet<User>();
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getRecommendationCount() { return recommendations.size(); }

    public boolean isRecommendedBy(User user) {
        return this.recommendations.contains(user);
    }

    public void addRecommendation(User user) { recommendations.add(user); }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", state='" + state + '\'' +
                ", recommendations='" + recommendations + '\'' +
                ", op=" + op +
                '}';
    }
}
