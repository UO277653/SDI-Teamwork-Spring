package com.sdi21.socialnetwork.entities;

import org.springframework.web.bind.annotation.Mapping;

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

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        ACCEPTED,
        PENDING;
    }

    public FriendRequest() {

    }

    public FriendRequest (User sender, User receiver, State state) {
        super();
        this.sender = sender;
        this.receiver = receiver;
        this.state = State.PENDING;
    }

    public FriendRequest (User sender, User receiver) {
        super();
        this.sender = sender;
        this.receiver = receiver;
        this.state = State.PENDING;
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

    public boolean isAccepted() { return this.state == State.ACCEPTED; }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

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
