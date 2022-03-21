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

    @Query("SELECT f FROM FriendRequest f WHERE (f.receiver = ?1 OR f.sender = ?1)")
    Page<FriendRequest> findAllByUser(Pageable pageable, User user);

    @Query("SELECT f FROM FriendRequest f WHERE (f.sender = ?1 AND f.receiver = ?2) OR (f.sender = ?2 AND f.receiver = ?1)")
    List<FriendRequest> findBySenderOrReceiver(User sender, User receiver);

    @Query("SELECT f FROM FriendRequest f WHERE f.state = ?3 AND ((f.sender = ?1 AND f.receiver = ?2) OR (f.sender = ?2 AND f.receiver = ?1))")
    List<FriendRequest> findBySenderOrReceiverAndAccepted(User sender, User receiver, FriendRequest.State state);

    String FindUserQuery =
            "SELECT u FROM User u WHERE u IN"
          + " (SELECT f.sender FROM FriendRequest f WHERE f.receiver = ?1 AND f.state = ?2) "
          + " OR u IN"
          + " (SELECT f.receiver FROM FriendRequest f WHERE f.sender = ?1 AND f.state = ?2) " ;
    @Query(value = FindUserQuery)
    Page<User> findFriendsForUser(Pageable pageable, User user, FriendRequest.State state);
    @Query(value = FindUserQuery)
    List<User> findFriendsForUser(User user, FriendRequest.State state);



    @Modifying
    @Transactional
    @Query("UPDATE FriendRequest SET state =?1 WHERE id=?2")
    void setFriendRequestState(FriendRequest.State state, Long id);
}
