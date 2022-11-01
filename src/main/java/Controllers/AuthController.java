package Controllers;

import Entities.UserEntity;
import Services.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

public class AuthController {
    private UserService service;

    public AuthController(UserService service){
        this.service = service;
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserEntity getAuthUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null){
            return null;
        }
        Object principal = auth.getPrincipal();
        UserEntity user = (principal instanceof User ? (UserEntity) principal : null);
        return Objects.nonNull(user) ? this.service.getByLogin(user.getLogin()) : null;
    }
}
