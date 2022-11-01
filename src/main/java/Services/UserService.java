package Services;

import Entities.UserEntity;
import Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = getByLogin(username);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException(String.format("User %s is not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, new HashSet<>());
    }
}
