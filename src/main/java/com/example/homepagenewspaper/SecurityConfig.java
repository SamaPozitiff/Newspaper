package com.example.homepagenewspaper;

import entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;
import repositories.UserRepository;
import services.ArticleServiceForRest;
import services.RestArticleService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig{

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    // Enable jdbc authentication
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource);
//    }



    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        //jdbcUserDetailsManager.setUserExistsSql("select email, password from newspaper_user where email = ?, password = ?");
        jdbcUserDetailsManager.setUsersByUsernameQuery("select email, password, TRUE from newspaper_user where email = ?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select email, true from newspaper_user where email = ?");

       return jdbcUserDetailsManager;
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withUsername("inna").
//                password(passwordEncoder().encode("123"))
//                .roles("USER").build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/register/*").permitAll()
                .antMatchers("/login/*").permitAll()
                    .antMatchers("/{article}/comments/add", "/articles/add", "/article/like/add").hasRole("USER")
                    .antMatchers("/", "/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //.exceptionHandling()
                //.and()
                .formLogin(Customizer.withDefaults())

//                .loginPage("/login")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/homepage.html",true)
//                .failureUrl("/login.html?error=true")
//                .and()
//                .logout()
//                .logoutUrl("/perform_logout")

                    .build();
    }
}
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepo) {
//        return email -> {
//            UserEntity user = userRepo.findByEmail(email);
//            if (user != null) {
//                return user;
//            }
//            throw new UsernameNotFoundException(
//                    "User '" + email + "' not found");
//        };
//    }



//.formLogin()
//        .loginPage("/login")
//        .loginProcessingUrl("/authenticate")
//        .usernameParameter("email")
//        .passwordParameter("password")
//        .and()
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        return http
//                .authorizeRequests()
//                    .antMatchers("/{article}/comments/add", "/articles/add", "/article/like/add").hasRole("USER")
//                    .antMatchers("/", "/**").permitAll()
//                .and()
//                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//                .oauth2Login(
//                        oath2Login -> oath2Login.loginPage("/oath2/authorization/newspaper/login"))
//                .oauth2Client(Customizer.withDefaults())
//                .exceptionHandling()
//                .and()
//                .formLogin(Customizer.withDefaults())
//                    .logout()
//                    .logoutSuccessUrl("/")
//                .and()
//                    .build();
//    }
//
//    @Bean
//    @RequestScope
//    public ArticleServiceForRest articleServiceForRest(OAuth2AuthorizedClientService clientService){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String accessToken = null;
//        if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)){
//            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//            String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
//            if (clientRegistrationId.equals("newspaper-admin-client")){
//                OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName());
//                accessToken = client.getAccessToken().getTokenValue();
//            }
//        }
//        return new RestArticleService(accessToken);
//    }
//
//
//}
