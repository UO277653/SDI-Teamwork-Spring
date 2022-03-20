package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PublicationsRepository extends CrudRepository<Publication,Long> {

    @Query("SELECT p FROM Publication p WHERE p.op.email LIKE ?1 AND p.state NOT LIKE 'Censurada'")
    Page<Publication> findByUserEmail(Pageable pageable, String email);

    Page<Publication> findAll(Pageable pageable);

    @Query("SELECT p from Publication p WHERE (LOWER(p.op.email) LIKE LOWER(?1) OR LOWER(p.title) LIKE LOWER(?1)) OR LOWER(p.state) LIKE LOWER(?1)")
    Page<Publication> findAll(Pageable pageable, String s);
}
