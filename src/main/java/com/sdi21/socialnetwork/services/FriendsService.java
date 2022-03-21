package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.FriendRequest;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FriendsService {

    @Autowired
    private RequestRepository requestRepository;

    @PostConstruct
    public void init() {}



    public void sendFriendRequest(User sender, User receiver) {
        FriendRequest request = new FriendRequest(sender, receiver);
        requestRepository.save(request);
    }

    public void addFriend(FriendRequest friendRequest) {
        friendRequest.setState(FriendRequest.State.ACCEPTED);
        requestRepository.save(friendRequest);
    }

    public Page<User> getFriendsForUser(Pageable pageable, User user) {
        return requestRepository.findFriendsForUser(pageable, user, FriendRequest.State.ACCEPTED);
    }

    public List<User> getFriendsForUser(User user) {
        return requestRepository.findFriendsForUser(user, FriendRequest.State.ACCEPTED);
    }

    public List<User> getPendingFriendsForUser(User user) {
        return requestRepository.findFriendsForUser(user, FriendRequest.State.PENDING);
    }

    public Page<FriendRequest> getFriendRequestsForUser(Pageable pageable, User user) {
        return requestRepository.findAllByUser(pageable, user);
    }

    public void addRequest(User sender, User receiver){
        requestRepository.save(new FriendRequest(sender, receiver));
    }

    public void setFriendRequestAccepted(Long id) {
        requestRepository.setFriendRequestState(FriendRequest.State.ACCEPTED, id);
    }

    public boolean areFriends(User userA, User userB) {
        List<FriendRequest> request = requestRepository.findBySenderOrReceiver(userA, userB);
        return request != null && request.size() != 0;
    }

}
