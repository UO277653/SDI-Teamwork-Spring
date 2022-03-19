package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class InsertSampleDataService {

    private final static String[] NAMES = {"Antonio", "Jose", "Francisco", "Juan", "Manuel", "Pedro", "Jesus", "Angel",
            "Miguel", "Javier", "Maria", "Maria Carmen", "Josefa", "Isabel", "Maria Dolores", "Carmen", "Francisca",
            "Maria Pilar", "Dolores", "Maria Jose"};

    private final static String[] SURNAMES = {"Garcia", "Martinez", "Lopez", "Sanchez", "Gonzalez", "Gomez",
            "Fernandez", "Moreno", "Jimenez", "Perez"};

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private PublicationsService publicationsService;

    @PostConstruct
    public void init() {

        User admin = new User("admin@email.com", "Admin", "Admin");
        admin.setRole(rolesService.getRoles()[1]);
        admin.setPassword("admin");
        usersService.addUser(admin);


        generateUsers(15);
        generatePublications(10); //10 for each user
    }

    private void generateUsers(int numberOfUsers) {
        for(int i = 1; i <= numberOfUsers; i++) {
            String name = NAMES[new Random().nextInt(NAMES.length)];
            String surname = SURNAMES[new Random().nextInt(SURNAMES.length)];
            String email = String.format("user%02d@email.com", i);
            User user = new User(email, name, surname);
            user.setRole(rolesService.getRoles()[0]); // ROLE_USER
            String password = String.format("user%02d", i);
            user.setPassword(password);
            user.setPasswordConfirm(password);
            usersService.addUser(user);
        }
    }

    private void generatePublications(int numberOfPublications) {
        List<User> users = usersService.findAllUserRole();
        for (User u : users){
            for(int i = 1; i <= numberOfPublications; i++){
                String title = String.format("Publication %d, %s", i, u.getName());
                String text = String.format("%d. My publication: %s", i, u.getEmail());
                Publication publication = new Publication(title, text);
                publication.setOp(u);
                publicationsService.addPublication(publication);
            }
        }
    }
}
