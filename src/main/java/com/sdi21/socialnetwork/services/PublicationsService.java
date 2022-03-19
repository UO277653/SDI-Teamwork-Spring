package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.repositories.PublicationsRepository;
import com.sdi21.socialnetwork.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationsService {

    @Autowired
    private PublicationsRepository publicationsRepository;

    @Autowired
    private UsersRepository usersRepository;


    public List<Publication> getPublications(){
        List<Publication> publications = new ArrayList<Publication>();
        publicationsRepository.findAll().forEach(publications::add);
        return publications;
    }



    public void addPublication(Publication publication){
        publicationsRepository.save(publication);
    }


    public List<Publication> getPublicationsByEmail(String email){
        return usersRepository.findByEmail(email).getPublications();
    }

    public Page<Publication> getPublicationsByEmail(Pageable pageable, String email){
        return publicationsRepository.findByUserEmail(pageable, email);
    }

    public Page<Publication> getPublications(Pageable pageable){

        return publicationsRepository.findAll(pageable);
    }

    public Optional<Publication> getPublicationById(Long id){

        return publicationsRepository.findById(id);
    }
}
