package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.FriendRequest;
import com.sdi21.socialnetwork.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository extends CrudRepository<FriendRequest, Long> {

    @Query("SELECT f FROM FriendRequest f WHERE f.receiver = ?1")
    Page<FriendRequest> findAllByUser(Pageable pageable, User user);

    @Query("SELECT f FROM FriendRequest f WHERE (f.sender = ?1 AND f.receiver = ?2) OR (f.sender = ?2 AND f.receiver = ?1)")
    List<FriendRequest> findBySenderAndReceiver(User sender, User receiver);

    @Query("SELECT f.sender FROM FriendRequest f WHERE f.receiver = ?1 AND f.state = 'ACCEPTED'")
    Page<User> findFriendsForUser(Pageable pageable, User user);

    @Query("SELECT f.sender FROM FriendRequest f WHERE f.receiver = ?1 AND f.state = 'ACCEPTED'")
    List<User> findFriendsForUser(User user);

    @Modifying
    @Transactional
    @Query("UPDATE FriendRequest SET state =?1 WHERE id=?2")
    void setFriendRequestState(FriendRequest.State state, Long id);
}
