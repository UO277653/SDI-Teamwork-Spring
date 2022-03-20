package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.FriendRequest;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.repositories.RequestRepository;
import org.hibernate.loader.plan.build.internal.LoadGraphLoadPlanBuildingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Page<FriendRequest> getFriendRequestsForUser(Pageable pageable, User user) {
        return requestRepository.findAllByUser(pageable, user);
    }

    public void addRequest(FriendRequest request){
        requestRepository.save(request);
    }

    public void setFriendRequestAccepted(Long id) {
        requestRepository.setFriendRequestState(FriendRequest.State.ACCEPTED, id);
    }



}
