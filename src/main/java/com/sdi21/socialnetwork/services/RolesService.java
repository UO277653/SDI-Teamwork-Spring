package com.sdi21.socialnetwork.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
    String[] roles = {"ROLE_USER", "ROLE_ADMIN"};
    String[] publicationStatus = {"Aceptada", "Moderada", "Censurada"};

    public String[] getRoles() {
        return roles;
    }

    public String[] getPublicationStatus(){
        return this.publicationStatus;
    }
}
