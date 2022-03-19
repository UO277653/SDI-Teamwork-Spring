package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.PublicationsService;
import com.sdi21.socialnetwork.services.RolesService;
import com.sdi21.socialnetwork.services.UsersService;
import com.sdi21.socialnetwork.validators.PublicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private RolesService rolesService;

    //Add publication -----------------
    @GetMapping(value ="/publication/add")
    public String addPublication(Model model){
        model.addAttribute("publication", new Publication());
        return "publication/add";
    }

    @PostMapping(value= "/publication/add")
    public String addPublication(@ModelAttribute Publication publication, BindingResult result, Model model, Principal principal){
        publicationValidator.validate(publication,result);

        //This will change when we can log in as an user
        //Parameter to add Principal principal
        //Uncomment this:
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        publication.setOp(user);

        if(result.hasErrors()){
            System.out.println(publication.toString());
            return "publication/add";
        }

        publication.setDate(new Date());
        publicationsService.addPublication(publication);
        return "home";
    }

    //List publication------
    @GetMapping("/publication/listown")
    public String getList(Model model, Pageable pageable, Principal principal){
        //This will change when we can log in as an user
        //Parameter to add Principal principal
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        Page<Publication> publications = publicationsService.getPublicationsByEmail(pageable, user.getEmail());
                // new PageImpl<>(user.getPublications());


        model.addAttribute("publicationsList", publications.getContent());
        model.addAttribute("page",publications );

        return "publication/listown";
    }

    @GetMapping("/publication/list/{id}")
    public String getList(Model model, @PathVariable Long id){

        User user = usersService.getUser(id);
        Page<Publication> publications = new PageImpl<>(user.getPublications());

        model.addAttribute("publicationsList", publications.getContent());
        model.addAttribute("page",publications );
        return "publication/list";
    }

    @GetMapping("/publication/list")
    public String getListPublications(Model model, Pageable pageable, @RequestParam(required = false) String searchTextPub){

        Page<Publication> publications;

        if(searchTextPub != null && !searchTextPub.isEmpty()) {
            publications = publicationsService.getPublicationsByText(pageable, searchTextPub);
        } else {
            publications = publicationsService.getPublications(pageable);
        }

        model.addAttribute("publicationsList", publications.getContent());
        model.addAttribute("page",publications);

        return "publication/list";
    }

    @GetMapping("/publication/accept/{id}")
    public String switchToAccepted(Model model, @PathVariable Long id, Pageable pageable){

        publicationsService.setPublicationState(id, rolesService.getPublicationStatus()[0]);

        return getListPublications(model, pageable, null);
    }

    @GetMapping("/publication/moderate/{id}")
    public String switchToModerate(Model model, @PathVariable Long id, Pageable pageable){

        publicationsService.setPublicationState(id, rolesService.getPublicationStatus()[1]);

        return getListPublications(model, pageable, null);
    }

    @GetMapping("/publication/censor/{id}")
    public String switchToCensored(Model model, @PathVariable Long id, Pageable pageable){

        publicationsService.setPublicationState(id, rolesService.getPublicationStatus()[2]);

        return getListPublications(model, pageable, null);
    }
}
