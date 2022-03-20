package com.sdi21.socialnetwork;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
                .anyRequest().authenticated()
            .and()
                .exceptionHandling().accessDeniedPage("/login").and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/user/list") //2. la redireccion segun admin o user se hace en el servicio
            .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login"); //3. redirigir a la página de login
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

}

