package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.FriendRequest;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.FriendsService;
import com.sdi21.socialnetwork.services.RequestService;
import com.sdi21.socialnetwork.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class RequestController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private RequestService requestService;


    @RequestMapping("/request/list")
    public String getFriendRequestList(Model model, Principal principal, Pageable pageable) {
        String email = principal.getName(); //email es getName de la autenticación
        User user = usersService.getUserByEmail(email);
        Page<FriendRequest> requests = requestService.getFriendRequestsForUser(pageable, user);

        model.addAttribute("page", requests);
        model.addAttribute("requestList", requests.getContent());
        return "request/list";
    }

    @GetMapping("/request/accept/{id}") //("/request/list")
    public String acceptFriendRequest(Model model, @PathVariable Long id, Principal principal, Pageable pageable) {
        requestService.setFriendRequestAccepted(id);
        return getFriendRequestList(model, principal, pageable); //"request/list";
    }

}
