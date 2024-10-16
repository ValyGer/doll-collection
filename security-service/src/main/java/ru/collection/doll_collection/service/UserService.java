package ru.collection.doll_collection.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.collection.doll_collection.dto.UserDto;
import ru.collection.doll_collection.dto.UserInitDto;
import ru.collection.doll_collection.dto.UserInitUpdateDto;


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
}
