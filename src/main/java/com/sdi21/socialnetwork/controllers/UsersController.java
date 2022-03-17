package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/user/list")
    public String getList(Model model, Pageable pageable) {
        //List<User> users = usersService.getUsersWithRole("ROLE_USER");
        //Page<User> users = usersService.getUsersWithRole(pageable, "ROLE_USER");
        Page<User> users = usersService.getUsers(pageable);
        model.addAttribute("userList", users.getContent());
        model.addAttribute("page", users);
        return "user/list";
    }

//    @RequestMapping("/admin/list")
//    public String getListAdmin(Model model) {
//
//        List<User> users = usersService.getUsers();
//        model.addAttribute("userList", users);
//        List<String> toDeleteUsers = new ArrayList<>();
//        model.addAttribute("toDeleteUsers", toDeleteUsers);
//
//        return "admin/list";
//    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public String deleteUser(Model model, @RequestParam Map<String,String> toDeleteUsers, Pageable pageable) {

        List<Long> idsToDelete = new ArrayList<>();

        for (var entry : toDeleteUsers.entrySet()) {
            idsToDelete.add(Long.valueOf(entry.getValue()));
        }

        usersService.deleteUsers(idsToDelete);

        Page<User> users = usersService.getUsersWithRole(pageable, "ROLE_USER");
        model.addAttribute("userList", users.getContent());
        model.addAttribute("page", users);

        return "user/list";
    }
}
