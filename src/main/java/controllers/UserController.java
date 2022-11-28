package controllers;

import entities.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserService;


@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping(path = "/users/add")
    public ResponseEntity<UserEntity> addArticle(@RequestParam String email, String password, String firstName, String lastName){
        return new ResponseEntity<>(userService.save(new UserEntity(password, firstName, lastName, email)), HttpStatus.CREATED);

    }




}
