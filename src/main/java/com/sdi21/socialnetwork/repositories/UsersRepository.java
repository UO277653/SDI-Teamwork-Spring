package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends CrudRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.role = ?1 ORDER BY u.email ASC")
    Page<User> findAllByRole(Pageable pageable, String role);

    User findByEmail(String email); //1. registrarse como usuario

    Page<User> findAll(Pageable pageable);

    @Query("SELECT u from User u WHERE LOWER(u.email) LIKE LOWER(?1) OR LOWER(u.name) LIKE LOWER(?1) OR LOWER(u.surname) LIKE LOWER(?1)")
    Page<User> findAll(Pageable pageable, String s);

    @Query("SELECT u from User u WHERE u <> ?1 AND u.role <> 'ROLE_ADMIN'")
    Page<User> findAll(Pageable pageable, User user);

    @Query("SELECT u from User u WHERE LOWER(u.email) LIKE LOWER(?1) OR LOWER(u.name) LIKE LOWER(?1) OR LOWER(u.surname) LIKE LOWER(?1)" +
            "AND u <> ?2 AND u.role <> 'ROLE_ADMIN'")
    Page<User> findAll(Pageable pageable, String s, User user);

    @Modifying
    @Transactional
    @Query("UPDATE Publication SET state = ?2 WHERE id = ?1")
    void setPublicationState(Long id, String publicationStatus);
}
