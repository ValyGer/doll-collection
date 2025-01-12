package ru.collection.doll_collection.dto.user;

import org.springframework.stereotype.Component;
import ru.collection.doll_collection.entity.User;

@Component
public class UserDtoWithRoleMapper {
    public UserDtoWithRole userToUserDtoWithRole(User user) {
        return UserDtoWithRole.builder()
                .id(user.getId())
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .userRole(user.getUserRole())
                .image(user.getImage())
                .build();
    }
}
