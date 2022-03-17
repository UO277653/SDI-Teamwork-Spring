package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.User;
import com.sdi21.socialnetwork.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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



    public Page<User> getUsers(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    public Page<User> getUsersWithRole(Pageable pageable, String role) {
        return usersRepository.findAllByRole(pageable, role);
    }

    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }

    public User getUserByUsername(String username){
        return usersRepository.findByUsername(username);
    }

    public void addUser(User user) {
        usersRepository.save(user);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    // Ej 5
    public void deleteUsers(List<Long> ids){
        usersRepository.deleteAllById(ids);

    }

    public User getDefaultUser() {
        return usersRepository.findByUsername("Default");
    }
}
