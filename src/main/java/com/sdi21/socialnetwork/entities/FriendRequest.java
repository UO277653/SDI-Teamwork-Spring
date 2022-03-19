package com.sdi21.socialnetwork.entities;

import javax.persistence.*;

@Entity
public class FriendRequest {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name ="sender")
    private User sender;
    @ManyToOne
    @JoinColumn(name ="receiver")
    private User receiver;
    private State state;

    public enum State {
        ACCEPTED,
        PENDING;
    }

    public FriendRequest() {

    }

    public FriendRequest (User sender, User receiver, State state) {
        super();
        this.sender = new User();
        this.receiver = new User();
        this.state = State.PENDING;
    }

    public FriendRequest (User sender, User receiver) {
        super();
        this.sender = new User();
        this.receiver = new User();
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", state=" + state +
                '}';
    }
}
