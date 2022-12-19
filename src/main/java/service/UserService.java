package service;

import entity.UserEntity;

public interface UserService {

    UserEntity save(UserEntity user);

    UserEntity findById(Long id);

    UserEntity getByEmail(String email);

    UserEntity newUser(String email, String password, String firstName, String lastName, String role);
}
