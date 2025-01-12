package ru.collection.doll_collection.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.collection.doll_collection.dto.user.UserDto;
import ru.collection.doll_collection.dto.user.UserDtoWithRole;
import ru.collection.doll_collection.dto.user.UserInitDto;
import ru.collection.doll_collection.dto.user.UserInitUpdateDto;
import ru.collection.doll_collection.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto findUserById(Long id);

    UserDto createUser(UserInitDto userInitDto);

    UserDto updateUserById(Long userId, UserInitUpdateDto userInitUpdateDto);

    void deleteUserById(Long userId);

    List<UserDto> findAllUsers();

    Optional<byte[]> findAvatar(Long id);

    // Security!
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    Long findUserByName(String userName);

    UserDtoWithRole findUserByIdForView(Long id);
}
