package me.lebedamm.budgetapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FirstController {

    @GetMapping
    public String message() {
        return "Приложение запущено!";
    }
    @GetMapping("/info")
    public String getPersonalInfo() {
        return "Лебедева Марина, CookingApp, 4.02.2023 (15:00), Книга рецептов";
    }
}

