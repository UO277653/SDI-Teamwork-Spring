package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.services.FriendsService;
import com.sdi21.socialnetwork.services.RolesService;
import com.sdi21.socialnetwork.services.SecurityService;
import com.sdi21.socialnetwork.services.UsersService;
import com.sdi21.socialnetwork.validators.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private FriendsService friendsService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @Autowired
    SecurityService securityService;

    /**
     * 1. Registrarse como usuario
     * @param model
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    /**
     * 1. Registrarse como usuario.
     *      No es posible el registro de usuarios con perfil de administrador.
     * @param user
     * @param result
     * @return
     */
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
        //TODO opciones de usuario
        return "home";
    }

    /**
     * 2. Inicio de sesión
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }


    @RequestMapping("/user/list")
    public String getList(Model model, Pageable pageable, @RequestParam(required = false) String searchText,
                          Principal principal) {
        Page<User> users;
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        if(searchText != null && !searchText.isEmpty()) {
            users = usersService.getUsersByText(pageable, searchText);
        } else {
            users = usersService.getUsers(pageable);
        }

        List<User> userFriends = friendsService.getFriendsForUser(user);

        model.addAttribute("userList", users.getContent());
        model.addAttribute("loggedUser", user);
        model.addAttribute("userFriends", userFriends);
        model.addAttribute("page", users);
        return "user/list";
    }

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

        return "redirect:/user/list";
    }


}
