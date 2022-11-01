package com.example.homepagenewspaper;

import Security.JwtCsrfFilter;
import Security.JwtTokenRepository;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@ComponentScan({"Controllers","Services","Security","Repositories"})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService service;

    @Autowired(required=true)
    private JwtTokenRepository tokenRepository;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Bean
    public PasswordEncoder devPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                    .addFilterAt(new JwtCsrfFilter(tokenRepository, resolver), CsrfFilter.class)
                    .csrf().ignoringAntMatchers("/**")
                .and()
                    .authorizeRequests()
                    .antMatchers("/auth/login")
                    .authenticated()
                .and()
                    .httpBasic()
                    .authenticationEntryPoint(((request, response, authException) -> resolver.resolveException(request, response, null, authException)));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(this.service);
    }

}
