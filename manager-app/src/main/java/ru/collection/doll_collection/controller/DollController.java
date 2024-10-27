package ru.collection.doll_collection.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.collection.doll_collection.client.BadRequestException;
import ru.collection.doll_collection.client.DollManagerClient;
import ru.collection.doll_collection.dto.doll.DollInputDto;
import ru.collection.doll_collection.dto.doll.DollInputUpdateDto;
import ru.collection.doll_collection.dto.doll.DollOutputDto;
import ru.collection.doll_collection.mapping_service.DtoMapping;
import ru.collection.doll_collection.service.DollSumService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/dolls")
@RequiredArgsConstructor
public class DollController {

    private final DollManagerClient dollManagerClient;
    private final DollSumService dollSumService;
    private final DtoMapping dtoMapping;


    // Получение страницы списка всех кукол
    @GetMapping
    public String fineAllDolls(Model model) {
        List<DollOutputDto> allDolls = this.dollManagerClient.getAllDolls();
        model.addAttribute("dolls", this.dollManagerClient.getAllDolls());
        model.addAttribute("sum", this.dollSumService.getSumAllDolls(allDolls));
        return "dolls/list_dolls";
    }

    // Получение страницы создания новой куклы
    @GetMapping("/new")
    public String getDollPageCreate() {
        return "dolls/new_doll";
    }

    // Создание новой куклы и возврат на страницу куклы
    @PostMapping("/new")
    public String createDoll(DollInputDto dollInputDto, Model model) throws IOException {
        try {
            return "redirect:/dolls/%d".formatted(this.dollManagerClient.createDoll(dollInputDto).getId());
        } catch (BadRequestException exception) {
            model.addAttribute("doll", dollInputDto);
            model.addAttribute("errors", exception.getErrors());
            return "dolls/new_doll";
        }
    }


    // Получение страницы куклы
    @GetMapping("/{dollId:\\d+}")
    public String fineDollById(Model model, @PathVariable("dollId") Integer dollId) {
        model.addAttribute("doll", this.dollManagerClient.getDollById(dollId));
        return "dolls/doll";
    }

    // Получение страницы изменения куклы
    @GetMapping("/{dollId:\\d+}/edit")
    public String getDollPageEdit(Model model, @PathVariable("dollId") Integer dollId) {
        model.addAttribute("doll", this.dollManagerClient.getDollById(dollId));
        return "dolls/edit_doll";
    }

    // Изменение данных куклы
    @PostMapping("/{dollId:\\d+}/edit")
    public String updateDollById(@PathVariable("dollId") Integer dollId, DollInputUpdateDto dollInputUpdateDto
            , Model model) throws IOException {
        try {
            this.dollManagerClient.updateDollById(dollId, dollInputUpdateDto);
            return "redirect:/dolls/%d".formatted(dollId);
        } catch (BadRequestException exception) {
            model.addAttribute("doll", dtoMapping.
                    dollInputToDollInputWithError(dollManagerClient.getDollById(dollId), dollInputUpdateDto));
            model.addAttribute("errors", exception.getErrors());
            return "dolls/edit_doll";
        }
    }

    // Удаление куклы
    @PostMapping("/{dollId:\\d+}/delete")
    public String deleteDollById(@PathVariable("dollId") Integer dollId) {
        this.dollManagerClient.deleteDollById(dollId);
        return "redirect:/dolls";
    }

    // Обработка ошибки кукла не найдена
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model,
                                               HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "errors/404";
    }

    // Обработка ошибки 500
    @ExceptionHandler(Throwable.class)
    public String handleNoSuchElementException(Throwable exception, Model model,
                                               HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("error", exception.getMessage());
        return "templates/errors/500";
    }

    // Создание и генерация отчета
    @PostMapping(value = "/createResponse")
    public String sendResponseFile() {
        dollManagerClient.createDataFile();
        return "dolls/send_success";
    }
}
