package com.sdi21.socialnetwork.controllers;

import com.google.j2objc.annotations.AutoreleasePool;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.FriendsService;
import com.sdi21.socialnetwork.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private FriendsService friendsService;

    @RequestMapping("/user/list")
    public String getList(Model model, Pageable pageable, @RequestParam(required = false) String searchText) {
        Page<User> users;
        if(searchText != null && !searchText.isEmpty()) {
            users = usersService.searchUsersByEmailNameAndSurnameWithRole(pageable,
                    searchText, "ROLE_USER");
        } else {
            users = usersService.getUsersWithRole(pageable, "ROLE_USER");
        }

        model.addAttribute("userList", users.getContent());
        model.addAttribute("page", users);
        return "user/list";
    }

}
