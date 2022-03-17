package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
        usersService.addUser(admin);
        User defaultUser = new User("Default","Default", "Default");
        usersService.addUser(defaultUser);

        Publication publication = new Publication("Default publication", "Default text");
        publication.setOp(defaultUser);
        publicationsService.addPublication(publication);

        Publication publication2 = new Publication("Default publication 2", "Default text 2");
        publication2.setOp(defaultUser);
        publicationsService.addPublication(publication2);

    }

    private void generateUsers(int numberOfUsers) {
        for(int i = 0; i < numberOfUsers; i++) {
            String name = NAMES[new Random().nextInt(NAMES.length)];
            String surname = SURNAMES[new Random().nextInt(SURNAMES.length)];
            String email = String.format("user%02d@email.com", i + 1);
            User user = new User(email, name, surname);
            user.setRole(rolesService.getRoles()[0]); // ROLE_USER
            //System.out.println(user);
            usersService.addUser(user);
        }
    }
}
