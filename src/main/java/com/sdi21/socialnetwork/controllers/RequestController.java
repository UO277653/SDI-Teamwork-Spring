package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.FriendRequest;
import com.sdi21.socialnetwork.entities.logtype.LogType;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.FriendsService;
import com.sdi21.socialnetwork.services.LoggerService;
import com.sdi21.socialnetwork.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class RequestController {

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FriendsService friendsService;


    @RequestMapping("/request/list")
    public String getFriendRequestList(Model model, Principal principal, Pageable pageable) {
        String email = principal.getName(); //email es getName de la autenticación
        User user = usersService.getUserByEmail(email);
        Page<FriendRequest> requests = friendsService.getFriendRequestsForUser(pageable, user);

        model.addAttribute("page", requests);
        model.addAttribute("requestList", requests.getContent());
        model.addAttribute("loggedUser", user);

        loggerService.addLog(LogType.PET, "GET: /request/list");
        return "request/list";
    }

    // Accept a request with a given id
    @GetMapping("/request/accept/{id}") //("/request/list")
    public String acceptFriendRequest(Model model, @PathVariable Long id, Principal principal, Pageable pageable) {
        friendsService.setFriendRequestAccepted(id);
        loggerService.addLog(LogType.PET, "GET: /request/accept/{id} - id: " + id);
        return getFriendRequestList(model, principal, pageable); //"request/list";
    }

    // Send request to an user id
    @GetMapping("/request/send/{id}") //("/request/list")
    public String sendFriendRequest(Model model, @PathVariable Long id, Principal principal, Pageable pageable) {

        String email = principal.getName(); //email es getName de la autenticación
        User loggedUser = usersService.getUserByEmail(email);
        User receiver = usersService.getUser(id);
        friendsService.sendFriendRequest(loggedUser, receiver);
        loggerService.addLog(LogType.PET, "GET: /request/send/{id} - id: " + id);
        return getFriendRequestList(model, principal, pageable); //"request/list";
    }

}
