package ru.collection.doll_collection.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.collection.doll_collection.dto.DollUpdateDto;
import ru.collection.doll_collection.dto.DollNewDto;
import ru.collection.doll_collection.service.DollService;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/dolls")
@RequiredArgsConstructor
public class DollController {

    private final DollService dollService;

    // Получение страницы списка всех кукол
    @GetMapping
    public String getAllDolls(Model model) {
        model.addAttribute("dolls", this.dollService.findAllDolls());
        return "dolls/list_dolls";
    }

    // Получение страницы создания новой куклы
    @GetMapping("/new")
    public String getDollPageCreate() {
        return "dolls/new_doll";
    }

    // Создание новой куклы и возврат на страницу куклы
    @PostMapping("/new")
    public String createDoll(@Valid DollNewDto dollNewDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("dolls", dollNewDto);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "dolls/new_doll";
        } else {
            return "redirect:/dolls/%d".formatted(this.dollService.createDoll(dollNewDto).getId());
        }
    }

    // Получение страницы куклы
    @GetMapping("/{dollId:\\d+}")
    public String getDollById(Model model, @PathVariable("dollId") Integer dollId) {
        model.addAttribute("doll", this.dollService.findDollById(dollId));
        return "dolls/doll";
    }

    // Получение страницы изменения куклы
    @GetMapping("/{dollId:\\d+}/edit")
    public String getDollPageEdit(Model model, @PathVariable("dollId") Integer dollId) {
        model.addAttribute("doll", this.dollService.findDollById(dollId));
        return "dolls/edit_doll";
    }

    // Изменение данных куклы
    @PostMapping("/{dollId:\\d+}/edit")
    public String updateDollById(@PathVariable("dollId") Integer dollId, @Valid DollUpdateDto dollUpdateDto) {
        this.dollService.updateDollById(dollId, dollUpdateDto);
        return "redirect:/dolls/%d".formatted(dollId);
    }

    // Удаление куклы
    @PostMapping("/{dollId:\\d+}/delete")
    public String deleteDollById(@PathVariable Integer dollId) {
        this.dollService.deleteDollById(dollId);
        return "redirect:/dolls";
    }

    // Обработка ошибки товар не найден
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model,
                                               HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "errors/404";
    }
}
