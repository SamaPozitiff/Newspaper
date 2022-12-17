package mapper;

import entity.UserEntity;
import org.springframework.stereotype.Component;
import rest_dto.UserDTO;
@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public UserDTO toUserDTO(UserEntity user) {
        if (user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(String.format("%s %s", user.getFirstName(), user.getLastName()));
        return userDTO;
    }
}
