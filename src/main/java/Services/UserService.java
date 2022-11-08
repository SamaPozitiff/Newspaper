package Services;

import Entities.UserEntity;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
