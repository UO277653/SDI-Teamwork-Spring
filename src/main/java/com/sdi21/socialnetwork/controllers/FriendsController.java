package com.sdi21.socialnetwork.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/friend/list")
    public String getList(Model model, Pageable pageable, Principal principal) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        Page<User> friends = friendsService.getFriendsForUser(pageable, user);

        model.addAttribute("friendList", friends.getContent());
        model.addAttribute("page", friends);

        loggerService.addLog(LogType.PET, "Get: /friend/list - " + email);

        return "friend/list";
    }

}
