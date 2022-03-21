package com.sdi21.socialnetwork.loghandlers;

import com.sdi21.socialnetwork.entities.logtype.LogType;
import com.sdi21.socialnetwork.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LogInSuccessfulHandler implements AuthenticationSuccessHandler {

    @Autowired
    private LoggerService loggerService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        loggerService.addLog(LogType.LOGIN_EX, "SUCCESSFUL LOGIN: " +  username);
        response.sendRedirect("/user/list");
    }
}
