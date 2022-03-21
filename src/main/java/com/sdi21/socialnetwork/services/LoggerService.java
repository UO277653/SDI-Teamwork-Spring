package com.sdi21.socialnetwork.services;

import com.sdi21.socialnetwork.entities.Log;
import com.sdi21.socialnetwork.entities.logtype.LogType;
import com.sdi21.socialnetwork.repositories.LoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerService {



    //private Logger log = LoggerFactory.getLogger(LoggerService.class);

    @Autowired
    LoggerRepository repo;

    public void addLog(LogType logType, String text){
        System.out.println(logType +  "  ---  " +  text);
        repo.save(new Log(logType,text));
    }



    public List<Log> getLogs(){
        return repo.getAll();
    }

    public void deleteLogs() {

        repo.deleteAll();
    }

    public List<Log> getLogsByType(LogType type) {

        return repo.getLogsWithType(type);
    }

}
