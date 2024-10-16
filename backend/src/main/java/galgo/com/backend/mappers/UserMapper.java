package galgo.com.backend.mappers;

import galgo.com.backend.dto.UserDTO;
import galgo.com.backend.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDTO> usersToUsersDTO(List<User> users);

    UserDTO userToUserDTO(User user);

    User userDtoToUser(UserDTO userDTO);



}
