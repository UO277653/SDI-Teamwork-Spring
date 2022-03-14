package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.Publication;
import com.sdi21.socialnetwork.repositories.PublicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationsService {

    @Autowired
    private PublicationsRepository publicationsRepository;


    public List<Publication> getPublications(){
        List<Publication> publications = new ArrayList<Publication>();
        publicationsRepository.findAll().forEach(publications::add);
        return publications;
    }

    public void addPublication(Publication publication){
        publicationsRepository.save(publication);
    }
}
