package mappers;

import entities.UserEntity;
import restDTO.UserDTO;

public interface UserMapper {

    UserDTO toUserDTO(UserEntity user);
}
