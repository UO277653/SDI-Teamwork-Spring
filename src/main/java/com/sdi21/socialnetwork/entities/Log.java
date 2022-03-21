package com.sdi21.socialnetwork.entities;

import com.sdi21.socialnetwork.entities.logtype.LogType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Log {

    @Id
    @GeneratedValue
    private long id;
    private LogType logType;
    private Date date;
    private String text;

    public Log(LogType logType, String text){
        this.logType = logType;
        this.date = new Date();
        this.text = text;
    }

    public Log() {

    }

    public long getId() {
        return id;
    }

    public LogType getLogType() {
        return logType;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}

