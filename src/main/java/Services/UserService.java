package Services;

import Entities.UserEntity;
import JPARepositories.UserJPARepository;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserJPARepository userRepository;


    public UserService(UserJPARepository userRepository){
        this.userRepository = userRepository;
    }


    public UserEntity getByEmail(String email){
        return userRepository.getUserByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = getByEmail(email);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException(String.format("User %s is not found", email));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, new HashSet<>());
    }
}
