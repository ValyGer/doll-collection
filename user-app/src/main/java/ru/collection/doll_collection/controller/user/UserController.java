package ru.collection.doll_collection.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.collection.doll_collection.constant.Role;
import ru.collection.doll_collection.dto.user.*;
import ru.collection.doll_collection.service.user.UserService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    // Получение страницы списка всех пользователей
    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", this.userService.findAllUsers());
        return "users/admin/list";
    }

    // Получение страницы создания нового пользователя
    @GetMapping("/new")
    public String getUserPageCreate() {
        return "users/user/new_user";
    }

    // Создание нового пользователя и возврат на страницу пользователя
    @PostMapping("/new")
    public String createUser(@Valid UserInitDto userInitDto) {
        UserDto userDto = this.userService.createUser(userInitDto);
        return "redirect:/users/user/%d".formatted(userDto.getId());
    }

    // Получение страницы пользователя
    @GetMapping("/{userId:\\d+}")
    public String getUserById(Model model, @PathVariable("userId") Long userId) {
        model.addAttribute("user", this.userService.findUserById(userId));
        return "users/user/user";
    }

    // Получение страницы изменения пользователя
    @GetMapping("/{userId:\\d+}/edit")
    public String getUserPageEdit(Model model, @PathVariable("userId") Long userId) {
        model.addAttribute("user", this.userService.findUserById(userId));
        return "users/user/edit_user";
    }

    // Изменение данных пользователя
    @PostMapping("/{userId:\\d+}/edit")
    public String updateUserById(@PathVariable("userId") Long userId, @Valid UserInitUpdateDto userInitUpdateDto) {
        this.userService.updateUserById(userId, userInitUpdateDto);
        return "redirect:/users/%d".formatted(userId);
    }

    // Удаление пользователя
    @PostMapping("/{userId:\\d+}/delete")
    public String deleteUserById(@PathVariable Long userId) {
        this.userService.deleteUserById(userId);
        return "redirect:/logout";
    }

    // Просмотр пользователя админом
    @GetMapping("/admin/{userId:\\d+}")
    public String getUserByIdForAdmin(Model model, @PathVariable("userId") Long userId) {
        UserDtoWithRole userDtoWithRole = this.userService.findUserByIdForView(userId);
        if (userDtoWithRole.getUserRole().equals(Role.ADMIN)) {
            model.addAttribute("user", userDtoMapper.userDtoWithRoleToUserDto(userDtoWithRole));
            return "users/admin/admin";
        } else {
            model.addAttribute("user", userDtoMapper.userDtoWithRoleToUserDto(userDtoWithRole));
            return "users/admin/user_admin";
        }
    }
}
