package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.Log;
import com.sdi21.socialnetwork.entities.logtype.LogType;
import com.sdi21.socialnetwork.repositories.LoggerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {



    //private Logger log = LoggerFactory.getLogger(LoggerService.class);

    @Autowired
    LoggerRepository repo;

    public void addLog(LogType logType, String text){
        repo.save(new Log(logType,text));
    }



}
