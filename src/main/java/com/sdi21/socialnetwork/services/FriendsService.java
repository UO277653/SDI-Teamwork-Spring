package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.FriendRequest;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.repositories.FriendsRepository;
import com.sdi21.socialnetwork.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private RequestRepository requestRepository;

    @PostConstruct
    public void init() {
        friendsRepository.save(new FriendRequest());
        //friendsRepository.save(new FriendRequest(new User("a@gmail.com", "N", "S"), new User("b@gmail.com", "N", "S"), FriendRequest.State.PENDING));
    }

    public Page<User> getFriendsForUser(Pageable pageable, User user) {
        return requestRepository.findFriendsForUser(pageable, user);
    }

}
