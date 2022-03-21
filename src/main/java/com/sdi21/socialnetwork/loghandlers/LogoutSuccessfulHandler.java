package com.sdi21.socialnetwork.loghandlers;

import com.sdi21.socialnetwork.entities.logtype.LogType;
import com.sdi21.socialnetwork.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LogoutSuccessfulHandler implements LogoutSuccessHandler {
    @Autowired
    private LoggerService loggerService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        loggerService.addLog(LogType.LOGOUT, "SUCCESSFUL LOGOUT: "+ authentication.getName());
        response.sendRedirect("/login?logout");
    }
}
