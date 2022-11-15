package com.example.homepagenewspaper;

import entities.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import repositories.UserRepository;

@Configuration
@EnableWebSecurity
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
    public SecurityFilterChain SecurityfilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests()
                    .antMatchers("/{article}/comments/add", "/articles/add", "/article/like/add").hasRole("USER")
                    .antMatchers("/", "/**").permitAll()
                .and()
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .exceptionHandling()
                .and()
                .formLogin(Customizer.withDefaults())
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .build();
    }


}
