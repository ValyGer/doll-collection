package ru.collection.doll_collection.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.collection.doll_collection.service.DollUserService;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("user/dolls")
public class UserController {

    private final DollUserService dollUserService;

    // Получение всех кукол
    @GetMapping
    public String fineAllDolls(Model model) {
        model.addAttribute("dolls", this.dollUserService.getAllDolls());
        return "dolls/list_dolls_by_user";
    }

    // Получение страницы куклы
    @GetMapping("/{dollId:\\d+}")
    public String fineDollById(Model model, @PathVariable("dollId") Integer dollId) {
        model.addAttribute("doll", this.dollUserService.getDollById(dollId));
        return "dolls/doll_by_user";
    }

    // Обработка ошибки товар не найден
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model,
                                               HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "templates/errors/404";
    }
}
