package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.FriendRequest;
import com.sdi21.socialnetwork.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<FriendRequest, Long> {

    @Query("SELECT f FROM FriendRequest f WHERE f.receiver = ?1")
    Page<FriendRequest> findAllByUser(Pageable pageable, User user);
}