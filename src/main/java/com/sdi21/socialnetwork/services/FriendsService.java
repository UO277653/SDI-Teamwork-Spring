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
    private UsersService usersService;

    @Autowired
    private RequestRepository requestRepository;

    @PostConstruct
    public void init() {
        //requestRepository.save(new FriendRequest());
        //friendsRepository.save(new FriendRequest(new User("a@gmail.com", "N", "S"), new User("b@gmail.com", "N", "S"), FriendRequest.State.PENDING));
    }



    public void sendFriendRequest(User sender, User receiver) {
        FriendRequest request = new FriendRequest(sender, receiver);
        requestRepository.save(request);
    }

    public void addFriend(FriendRequest friendRequest) {
        friendRequest.setState(FriendRequest.State.ACCEPTED);
        requestRepository.save(friendRequest);
        //usersService.saveUser(friendRequest.getSender());
        //usersService.saveUser(friendRequest.getReceiver());
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
        if (request == null || request.size() == 0)
            return false;
        return true;
    }

    public boolean areFriends(User userA, User userB, FriendRequest.State state) {
        List<FriendRequest> request = requestRepository.findBySenderOrReceiverAndAccepted(userA, userB, state);
        if (request == null || request.size() == 0)
            return false;
        return true;
    }

}
