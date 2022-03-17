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

    @PostConstruct
    public void init(){
        usersRepository.save(new User("sara@uniovi.es", "Sara", "González"));
    }



    public Page<User> getUsers(Pageable pageable) {
        return usersRepository.findAll(pageable);
  
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
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

    // Ej 5
    public void deleteUsers(List<Long> ids){
        usersRepository.deleteAllById(ids);

    }

    public User getDefaultUser() {
        return usersRepository.findByEmail("sara@uniovi.es");
    }

    public Page<User> searchUsersByEmailNameAndSurnameWithRole(
            Pageable pageable, String searchText, String role) {
        return usersRepository.searchByEmailNameAndSurnameWithRole(pageable, '%'+searchText+'%', role);
    }

    public void deleteAll(){
        usersRepository.deleteAll();
    }

    public User getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }
}
