package mapper;

import entity.UserEntity;
import rest.dto.UserDTO;

public interface UserMapper {

    UserDTO toUserDTO(UserEntity user);
}
