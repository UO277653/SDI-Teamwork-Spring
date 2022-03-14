package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
    List<User> findAllByRole(String role);
}
