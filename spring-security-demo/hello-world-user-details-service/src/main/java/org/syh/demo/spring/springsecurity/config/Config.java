package org.syh.demo.spring.springsecurity.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.syh.demo.spring.springsecurity.model.User;
import org.syh.demo.spring.springsecurity.services.InMemoryUserDetailsService;

@Configuration
public class Config{
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = new User("ben", "discrete-squirrel", "read");
        List<UserDetails> users = List.of(user);
        return new InMemoryUserDetailsService(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
