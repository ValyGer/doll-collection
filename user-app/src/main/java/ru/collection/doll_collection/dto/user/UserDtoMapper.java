package ru.collection.doll_collection.dto.user;

import org.springframework.stereotype.Component;
import ru.collection.doll_collection.entity.User;


@Component
public class UserDtoMapper {

    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .image(user.getImage())
                .build();
    }

    public UserDto userDtoWithRoleToUserDto(UserDtoWithRole userDtoWithRole) {
        return UserDto.builder()
                .id(userDtoWithRole.getId())
                .userEmail(userDtoWithRole.getUserEmail())
                .userName(userDtoWithRole.getUserName())
                .image(userDtoWithRole.getImage())
                .build();
    }
}
