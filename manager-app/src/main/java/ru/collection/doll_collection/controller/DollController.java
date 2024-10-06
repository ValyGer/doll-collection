package ru.collection.doll_collection.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.collection.doll_collection.entity.Doll;
import ru.collection.doll_collection.repository.DollRepositoryImpl;

@Controller
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class DollController {

    private final DollRepositoryImpl dollRepository;



    @GetMapping
    public void createDoll() {
        Doll doll = new Doll(1, 2024, "Mettel", "Barby", "BMR", "Barby", 2500L);
        System.out.println(dollRepository.saveDoll(doll));
    }

    // Получение страницы списка всех пользователей
    @GetMapping
    public String getAllDolls(Model model) {
        model.addAttribute("dolls", this.dollService.findAllDolls());
        return "dolls/list";
    }


}
