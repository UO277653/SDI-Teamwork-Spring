package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.repositories.PublicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationsService {

    @Autowired
    private PublicationsRepository publicationsRepository;


    public void addPublication(Publication publication){
        publicationsRepository.save(publication);
    }
}
