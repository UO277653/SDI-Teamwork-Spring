package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;


}
