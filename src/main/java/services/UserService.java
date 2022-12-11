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
    /*
    получить id авторизованного пользователя
     */
    public Long getIdAuthorizedUser(){
        UserEntity authUser = repository.findByEmail(securityConfig.getCurrentUsername());
        return authUser.getId();
    }

    public UserEntity getByEmail(String email){
        return repository.findByEmail(email);
    }

    public UserEntity newUser(String email, String password, String firstName, String lastName, String role){
        return new UserEntity(email, password, firstName, lastName, role);
    }

    public void delete(UserEntity user){
        repository.delete(user);
    }
}
