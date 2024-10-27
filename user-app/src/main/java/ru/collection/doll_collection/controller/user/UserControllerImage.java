package ru.collection.doll_collection.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.collection.doll_collection.service.user.UserService;


@RestController
@RequiredArgsConstructor
@RequestMapping("rest")
public class UserControllerImage {

    private final UserService userService;

    // Работа с картинкой
    @GetMapping(value = "/users/{userId}/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findAvatar(@PathVariable("userId") Long userId) {
        return userService.findAvatar(userId).orElseThrow(RuntimeException::new);
    }
}
