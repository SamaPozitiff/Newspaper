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

    public UserEntity save(UserEntity user){
        return repository.save(user);
    }

    public UserEntity findById(Long id){
        return repository.findById(id).get();
    }
}
