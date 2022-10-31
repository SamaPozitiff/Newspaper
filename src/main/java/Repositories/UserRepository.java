package Repositories;

import Entities.UserEntity;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List <UserEntity> users;

    public UserRepository(){
        this.users = new ArrayList();
        users.add(new UserEntity(1L,"Cat", "Meow", "Cat", "Meow", "cat@gmail.com"));
        users.add(new UserEntity(2L, "Dog", "Bark", "Dog", "Bark", "dog@gmail.com"));
    }

    public UserEntity getByLogin(String login){
        return this.users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst()
                .orElse(null);
    }
    public List<UserEntity> getAll(){
        return users;
    }
}
