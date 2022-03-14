package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;


    @PostConstruct
    public void init(){
        usersRepository.save(new User("Default"));

    }


    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }

    public User getUserByUsername(String username){
        return usersRepository.findByUsername(username);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    public User getDefaultUser() {
        return usersRepository.findByUsername("Default");
    }
}
