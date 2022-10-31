package Services;

import Entities.UserEntity;
import Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepositoryRest;
    public UserService(UserRepository userRepositoryRest){
        this.userRepositoryRest = userRepositoryRest;
    }

    public List<UserEntity> getAll(){
        return this.userRepositoryRest.getAll();
    }

    public UserEntity getByLogin(String login){
        return this.userRepositoryRest.getByLogin(login);
    }


}
