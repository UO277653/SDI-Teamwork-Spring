package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.Log;
import com.sdi21.socialnetwork.entities.logtype.LogType;
import com.sdi21.socialnetwork.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LogController {

    @Autowired
    private LoggerService loggerService;

    @RequestMapping(value = "/logger/list", method = RequestMethod.GET)
    public String listLogs(Model model) {

        loggerService.addLog(LogType.PET, "GET: /logger/list");

        model.addAttribute("logList", loggerService.getLogs());

        return "/logger/list";
    }

    @RequestMapping(value = "/logger/delete", method = RequestMethod.POST)
    public String deleteLogs(Model model) {

        loggerService.addLog(LogType.PET, "POST: /logger/delete");

        loggerService.deleteLogs();

        return "/logger/list";
    }

    @RequestMapping(value = "/logger/list/{type}", method = RequestMethod.POST)
    public String filterLogs(Model model, @PathVariable String type) {

        List<Log> logs = null;

        if(type.equals(LogType.PET.toString())){
            logs = loggerService.getLogsByType(LogType.PET);
        } else if(type.equals(LogType.ALTA.toString())){
            logs = loggerService.getLogsByType(LogType.ALTA);
        } else if(type.equals(LogType.LOGIN_EX.toString())){
            logs = loggerService.getLogsByType(LogType.LOGIN_EX);
        } else if(type.equals(LogType.LOGIN_ERR.toString())){
            logs = loggerService.getLogsByType(LogType.LOGIN_ERR);
        } else if(type.equals(LogType.LOGOUT.toString())){
            logs = loggerService.getLogsByType(LogType.LOGOUT);
        }

        model.addAttribute("logList", logs);
        
        loggerService.addLog(LogType.PET, "POST: /publication/recommend/{id} - type: " + type);

        return "/logger/list";
    }
}
