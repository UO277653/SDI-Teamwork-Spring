package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.FriendRequest;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.repositories.FriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UsersService usersService;

    @PostConstruct
    public void init() {
        friendsRepository.save(new FriendRequest());
    }

    public void addFriend(FriendRequest friendRequest) {
        usersService.addFriend(friendRequest.getReceiver(), friendRequest.getSender());
    }
}
