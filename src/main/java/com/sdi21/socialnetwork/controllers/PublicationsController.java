package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.PublicationsService;
import com.sdi21.socialnetwork.services.UsersService;
import com.sdi21.socialnetwork.validators.PublicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Date;

@Controller
public class PublicationsController {

    @Autowired
    public PublicationValidator publicationValidator;

    @Autowired
    private PublicationsService publicationsService;

    @Autowired
    private UsersService usersService;



    //Add publication -----------------
    @GetMapping(value ="/publication/add")
    public String addPublication(Model model){
        model.addAttribute("publication", new Publication());
        return "publication/add";
    }

    @PostMapping(value= "/publication/add")
    public String addPublication(@ModelAttribute Publication publication, BindingResult result, Model model){
        publicationValidator.validate(publication,result);

        //This will change when we can log in as an user
        //Parameter to add Principal principal
        //Uncomment this:
        //  String username = principal.getName();
        //  User user = usersService.getUserByEmail(email);
        User user = usersService.getDefaultUser();
        publication.setOp(user);

        if(result.hasErrors()){
            System.out.println(publication.toString());
            return "publication/add";
        }

        publication.setDate(new Date());
        publicationsService.addPublication(publication);
        return "home";
    }




}
