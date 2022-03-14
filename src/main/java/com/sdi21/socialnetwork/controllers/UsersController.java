package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.error.Mark;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/user/list")
    public String getList(Model model, Pageable pageable) {
        //List<User> users = usersService.getUsersWithRole("ROLE_USER");
        Page<User> users = usersService.getUsersWithRole(pageable, "ROLE_USER");
        model.addAttribute("userList", users.getContent());
        model.addAttribute("page", users);
        return "user/list";
    }

}
