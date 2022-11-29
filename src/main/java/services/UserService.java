package services;

import security.SecurityConfig;
import entities.UserEntity;
import repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    UserRepository repository;
    SecurityConfig securityConfig;

    public UserService(UserRepository repository, SecurityConfig securityConfig){

        this.repository = repository;
        this.securityConfig = securityConfig;
    }

    public UserEntity save(UserEntity user){
        return repository.save(user);
    }

    public UserEntity findById(Long id){
        return repository.findById(id).get();
    }

    public String getNameAuthorizedUser(){
        UserEntity authUser = repository.findByEmail(securityConfig.getCurrentUsername());
        return String.format(new String("%s %s"), authUser.getFirstName(), authUser.getLastName());
    }

    public Long getIdAuthorizedUser(){
        UserEntity authUser = repository.findByEmail(securityConfig.getCurrentUsername());
        return authUser.getId();
    }
}
