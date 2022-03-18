package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.RolesService;
import com.sdi21.socialnetwork.services.SecurityService;
import com.sdi21.socialnetwork.services.UsersService;
import com.sdi21.socialnetwork.validators.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @Autowired
    SecurityService securityService;

    //1. Registrarse como usuario
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    //1. Registrarse como usuario
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result) {
        signUpFormValidator.validate(user, result);
        if (result.hasErrors()){
            return "signup";
        }
        user.setRole(rolesService.getRoles()[0]); //user role
        usersService.addUser(user);
        securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
        return "redirect:/home";
    }

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }
  
    @RequestMapping("/user/list")
    public String getList(Model model, Pageable pageable, @RequestParam(required = false) String searchText) {
        Page<User> users = usersService.getUsers(pageable);

        //if(searchText != null && !searchText.isEmpty()) {
        //    users = usersService.searchUsersByEmailNameAndSurnameWithRole(pageable,
        //            searchText, "ROLE_USER");
        //} else {
        //    users = usersService.getUsersWithRole(pageable, "ROLE_USER");
        //}

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
