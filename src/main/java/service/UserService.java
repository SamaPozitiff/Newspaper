package service;

import jwt_auth.SecurityConfig;
import entity.UserEntity;
import repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    UserRepository repository;
    SecurityConfig securityConfig;

    public UserService(UserRepository repository, SecurityConfig securityConfig){

        this.repository = repository;
        this.securityConfig = securityConfig;
    }
    /*
    сохранение пользователя
     */
    public UserEntity save(UserEntity user){
        return repository.save(user);
    }
    /*
    найти пользователя по id
     */
    public UserEntity findById(Long id){
        return repository.findById(id).get();
    }

    public UserEntity getByEmail(String email){
        return repository.findByEmail(email);
    }

    public UserEntity newUser(String email, String password, String firstName, String lastName, String role){
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        return user;
    }
}
