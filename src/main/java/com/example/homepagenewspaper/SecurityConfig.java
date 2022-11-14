package com.example.homepagenewspaper;

import entities.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import repositories.UserRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return email -> {
            UserEntity user = userRepo.findByEmail(email);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException(
                    "User '" + email + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests()
                    .antMatchers("/{article}/comments/add", "/articles/add", "/article/like/add").hasRole("USER")
                    .antMatchers("/", "/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    .usernameParameter("email")
                    .passwordParameter("password")
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .build();
    }
}
