package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
    //List<User> findAllByRole(String role);
    @Query("SELECT u FROM User u WHERE u.role = ?1 ORDER BY u.username ASC")
    Page<User> findAllByRole(Pageable pageable, String role);
}
