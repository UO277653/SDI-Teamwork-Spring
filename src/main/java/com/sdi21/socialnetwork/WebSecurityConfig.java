package com.sdi21.socialnetwork;

import com.sdi21.socialnetwork.entities.logtype.LogType;
import com.sdi21.socialnetwork.loghandlers.LogInSuccessfulHandler;
import com.sdi21.socialnetwork.loghandlers.LogInUnsuccessfulHandler;
import com.sdi21.socialnetwork.loghandlers.LogoutSuccessfulHandler;
import com.sdi21.socialnetwork.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LogInSuccessfulHandler logInSuccessfulHandler;
    @Autowired
    private LogInUnsuccessfulHandler logInUnsuccessfulHandler;
    @Autowired
    private LogoutSuccessfulHandler logoutSuccessfulHandler;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/script/**", "/", "/signup", "/login/**").permitAll()
                .antMatchers("/user/delete/*").hasAuthority("ROLE_ADMIN")
                .antMatchers("/publication/list").hasAuthority("ROLE_ADMIN")
                .antMatchers("/publication/accept/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/publication/moderate/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/publication/censor/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/logger/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/login").and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/user/list")//2. la redireccion segun admin o user se hace en el servicio
                .successHandler(logInSuccessfulHandler)
                .failureHandler(logInUnsuccessfulHandler)
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(logoutSuccessfulHandler)
                .permitAll()
        ; //3. redirigir a la página de login
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

}

