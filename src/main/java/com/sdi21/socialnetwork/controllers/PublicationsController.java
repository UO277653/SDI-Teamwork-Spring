package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.services.PublicationsService;
import com.sdi21.socialnetwork.validators.PublicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicationsController {

    @Autowired
    public PublicationValidator publicationValidator;

    @Autowired
    private PublicationsService publicationsService;



    //Add publication -----------------
    @GetMapping(value ="/publication/add")
    public String addPublication(Publication publication){

        return "publication/add";
    }

    @PostMapping(value= "/publication/add")
    public String addPublication(@ModelAttribute Publication publication, BindingResult result, Model model){
        publicationValidator.validate(publication,result);

        if(result.hasErrors()){
            return "publication/add";
        }

        publicationsService.addPublication(publication);
        return "home";
    }




}
