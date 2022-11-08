package services;

import entities.UserEntity;
import repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public void save(UserEntity user){
        repository.save(user);
    }
}
