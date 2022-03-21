package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.logtype.LogType;
import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.*;
import com.sdi21.socialnetwork.validators.PublicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private LoggerService loggerService;


    @Autowired
    public PublicationValidator publicationValidator;

    @Autowired
    private PublicationsService publicationsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private RolesService rolesService;

    //Add publication -----------------
    @GetMapping(value ="/publication/add")
    public String addPublication(Model model){
        model.addAttribute("publication", new Publication());
        loggerService.addLog(LogType.PET, "GET: /publication/add");
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

        loggerService.addLog(LogType.PET, "POST: /publication/add" + publication.toString());
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
        model.addAttribute("user", user);
        model.addAttribute("page",publications );

        loggerService.addLog(LogType.PET, "GET: /publication/listown");
        return "publication/listown";
    }

    @GetMapping("/publication/list/{id}")
    public String getList(Model model, @PathVariable Long id, Pageable pageable, Principal principal){

        User user = usersService.getUser(id);
        Page<Publication> publications = publicationsService.getPublicPublicationsByEmail(pageable, user.getEmail());

        String email = principal.getName();
        User loggedUser = usersService.getUserByEmail(email);

        if(!friendsService.areFriends(user, loggedUser)){
            return "home";
        }

        model.addAttribute("publicationsList", publications.getContent());
        model.addAttribute("page",publications );
        model.addAttribute("user", loggedUser);

        loggerService.addLog(LogType.PET, "GET: /publication/list/{id} - id: " + id );
        return "publication/list";
    }

    @GetMapping("/publication/recommend/{id}")
    public String recommendPublication(Model model, Principal principal, @PathVariable Long id, Pageable pageable) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        publicationsService.addRecommendation(id, user);

        loggerService.addLog(LogType.PET, "GET: /publication/recommend/{id} - id: " + id );
        return getListPublications(model, principal, pageable, null);
    }

    @GetMapping("/publication/list")
    public String getListPublications(Model model, Principal principal, Pageable pageable, @RequestParam(required = false) String searchTextPub){

        Page<Publication> publications;

        if(searchTextPub != null && !searchTextPub.isEmpty()) {
            publications = publicationsService.getPublicationsByText(pageable, searchTextPub);
        } else {
            publications = publicationsService.getPublications(pageable);
        }

        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        model.addAttribute("publicationsList", publications.getContent());
        model.addAttribute("page",publications);
        model.addAttribute("user", user);
        loggerService.addLog(LogType.PET, "GET: /publication/list - searchTextPub:" + searchTextPub );
        return "publication/list";
    }

    @GetMapping("/publication/accept/{id}")
    public String switchToAccepted(Model model, @PathVariable Long id, Pageable pageable, Principal principal){

        publicationsService.setPublicationState(id, rolesService.getPublicationStatus()[0]);

        loggerService.addLog(LogType.PET, "GET: /publication/accept/{id} - id:" + id );
        return getListPublications(model, principal, pageable, null);
    }

    @GetMapping("/publication/moderate/{id}")
    public String switchToModerate(Model model, @PathVariable Long id, Pageable pageable, Principal principal){

        publicationsService.setPublicationState(id, rolesService.getPublicationStatus()[1]);

        loggerService.addLog(LogType.PET, "GET: /publication/moderate/{id} - id:" + id );
        return getListPublications(model, principal, pageable, null);
    }

    @GetMapping("/publication/censor/{id}")
    public String switchToCensored(Model model, @PathVariable Long id, Pageable pageable, Principal principal){

        publicationsService.setPublicationState(id, rolesService.getPublicationStatus()[2]);

        loggerService.addLog(LogType.PET, "GET: /publication/censor/{id} - id:" + id );
        return getListPublications(model, principal, pageable, null);
    }
}
