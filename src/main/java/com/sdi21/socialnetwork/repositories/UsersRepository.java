package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User,Long> {

    //List<User> findAllByRole(String role);
    @Query("SELECT u FROM User u WHERE u.role = ?1 ORDER BY u.email ASC")
    Page<User> findAllByRole(Pageable pageable, String role);

    @Query("SELECT u FROM User u WHERE (LOWER(u.email) LIKE LOWER(?1) OR LOWER(u.name) LIKE LOWER(?1))" +
            " OR LOWER(u.surname) LIKE LOWER(?1) AND u.role = ?2")

    Page<User> searchByUsernameNameAndSurnameWithRole(Pageable pageable, String searchText, String role);

    User findByEmail(String email); //1. registrarse como usuario

    Page<User> findAll(Pageable pageable);

    //Page<User> searchByEmailNameAndSurnameWithRole(Pageable pageable, String searchText, String role);
}
