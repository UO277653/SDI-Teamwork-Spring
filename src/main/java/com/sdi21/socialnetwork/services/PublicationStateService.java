package com.sdi21.socialnetwork.services;

import org.springframework.stereotype.Service;

@Service
public class PublicationStateService {
    String[] states = {"Aceptada", "Moderada", "Censurada"};
    public String[] getStates() {
        return states;
    }
}
