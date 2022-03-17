package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.FriendRequest;
import org.springframework.data.repository.CrudRepository;

public interface FriendsRepository extends CrudRepository<FriendRequest, Long> {


}
