package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RolesService rolesService;


    public Page<User> getUsers(Pageable pageable, User user) {
        return user.getRole().equals("ROLE_ADMIN") ? usersRepository.findAll(pageable) :
                usersRepository.findAll(pageable, user);
    }

    public Page<User> getUsersByText(Pageable pageable, String searchText, User user) {
        return user.getRole().equals("ROLE_ADMIN") ? usersRepository.findAll(pageable, '%'+searchText+'%') :
                usersRepository.findAll(pageable, '%'+searchText+'%', user);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }

    public List<User> findAllUserRole() {
        List<User> all = getUsers();
        List<User> result = new ArrayList<User>();
        for(User u : all){
            if (u.getRole().equals("ROLE_USER"))
                result.add(u);
        }
        return result;
    }

    public Page<User> getUsersWithRole(Pageable pageable, String role) {
        return usersRepository.findAllByRole(pageable, role);
    }

    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    public User getDefaultUser() {
        return usersRepository.findByEmail("sara@uniovi.es");
    }


    public void deleteAll(){
        usersRepository.deleteAll();
    }

    public User getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public void deleteUsers(List<Long> ids){
        usersRepository.deleteAllById(ids);

    }

    public void addFriend (User receiver, User sender) {
        usersRepository.save(receiver);
        usersRepository.save(sender);
        //receiver.getFriends().add(sender);
        //sender.getFriends().add(receiver);
    }

    public boolean areFriends(User user, User loggedUser) {
        //HAS TO BE IMPLEMENTED
        return true;
    }
}
