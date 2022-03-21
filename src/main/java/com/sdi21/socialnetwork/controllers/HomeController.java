package com.sdi21.socialnetwork.controllers;

import com.sdi21.socialnetwork.entities.logtype.LogType;
import com.sdi21.socialnetwork.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private LoggerService loggerService;

    @RequestMapping("/")
    public String index(){
        loggerService.addLog(LogType.PET, "Get: /");
        return "index";
    }
}
