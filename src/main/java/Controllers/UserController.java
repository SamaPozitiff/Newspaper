package Controllers;

import Entities.UserEntity;
import Services.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;
@RestController
public class UserController {
    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<UserEntity> getAll(){
        return this.service.getAll();
    }



    //public String test(){
    //    return "заработало?";
    //}



}
