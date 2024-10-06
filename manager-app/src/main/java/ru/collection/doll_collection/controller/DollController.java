package ru.collection.doll_collection.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.service.DollService;

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
    public String createDoll(DollDto dollDto) {
        return "redirect:/dolls/%d".formatted(this.dollService.createDoll(dollDto).getId());
    }

    // Получение страницы куклы
    @GetMapping("/{dollId:\\d+}")
    public String getDollById(Model model, @PathVariable("dollId") Long dollId) {
        model.addAttribute("doll", this.dollService.findDollById(dollId));
        return "dolls/doll";
    }

    // Получение страницы изменения куклы
    @GetMapping("/{dollId:\\d+}/edit")
    public String getDollPageEdit(Model model, @PathVariable("dollId") Long dollId) {
        model.addAttribute("doll", this.dollService.findDollById(dollId));
        return "dolls/edit_doll";
    }

    // Изменение данных куклы
    @PostMapping("/{dollId:\\d+}/edit")
    public String updateDollById(@PathVariable("dollId") Long dollId, DollDto dollDto) {
        this.dollService.updateDollById(dollId, dollDto);
        return "redirect:/dolls/%d".formatted(dollId);
    }

    // Удаление куклы
    @PostMapping("/{dollId:\\d+}/delete")
    public String deleteDollById(@PathVariable Long dollId) {
        this.dollService.deleteDollById(dollId);
        return "redirect:/dolls";
    }
}
