package mappers;

import entities.UserEntity;
import org.springframework.stereotype.Component;
import restDTO.UserDTO;
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
