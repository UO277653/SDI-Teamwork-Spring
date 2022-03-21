package com.sdi21.socialnetwork.repositories;

import com.sdi21.socialnetwork.entities.Log;
import com.sdi21.socialnetwork.entities.logtype.LogType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoggerRepository extends CrudRepository<Log, Long> {

    @Query("SELECT l FROM Log l ORDER BY l.date desc")
    public List<Log> getAll();

    @Query("SELECT l FROM Log l WHERE l.logType=?1 ORDER BY l.date desc")
    List<Log> getLogsWithType(LogType typeOfLog);
}
