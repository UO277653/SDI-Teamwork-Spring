package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FriendsController {

    @Autowired
    private FriendsService friendsService;


}
