package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.FriendRequest;
import com.sdi21.socialnetwork.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
    private FriendsService friendsService;

    @Autowired
    private RequestService requestService;

    @PostConstruct
    public void init() {
        generateUsers(15);
        generateRequests(15);
        User admin = new User("admin@email.com", "Admin", "Admin");
        admin.setRole(rolesService.getRoles()[1]);
        usersService.addUser(admin);

        User user1 = new User("a@gmail.com", "N", "S");
        User user2 = new User("b@gmail.com", "Na", "Su");
        User user3 = new User("c@gmail.com", "Nam", "Sur");
        User user4 = new User("d@gmail.com", "Name", "Surn");

        FriendRequest fr1 = new FriendRequest(user1, user2, FriendRequest.State.PENDING);
        FriendRequest fr2 = new FriendRequest(user3, user4, FriendRequest.State.PENDING);

        friendsService.addFriend(fr1);
        friendsService.addFriend(fr2);
        requestService.addRequest(fr1);
        requestService.addRequest(fr2);
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

    private void generateRequests(int numberOfRequests) {
        for (int i = 0; i < numberOfRequests; i++) {
            String name = NAMES[new Random().nextInt(NAMES.length)];
            String surname = SURNAMES[new Random().nextInt(SURNAMES.length)];
            String email = String.format("user%02d@email.com", i + 1);
            User user = new User(email, name, surname);
            user.setRole(rolesService.getRoles()[0]); // ROLE_USER
            //usersService.addUser(user);
            User user2 = new User("a@gmail.com", "N", "S");

            FriendRequest.State state = FriendRequest.State.PENDING;
            FriendRequest fr = new FriendRequest(user, user, state);
            //usersService.addFriend(user, user2);
            //friendsService.addFriend(fr);
            //usersService.addUser(user);
            //requestService.getFriendRequestsForUser(pageable, user);
        }
    }
}
