package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PublicationsRepository extends CrudRepository<Publication,Long> {

    @Query("SELECT p FROM Publication p WHERE p.op.email LIKE ?1")
    Page<Publication> findByUserEmail(Pageable pageable, String email);
}
