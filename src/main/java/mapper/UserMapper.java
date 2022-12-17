package mapper;

import entity.UserEntity;
import rest_dto.UserDTO;

public interface UserMapper {

    UserDTO toUserDTO(UserEntity user);
}
