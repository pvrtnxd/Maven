package me.lebedamm.budgetapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FirstController {

    @GetMapping
    public String Message() {
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public String GetInfo() {
        return "Лебедева Марина, CookingApp, 4.02.2023, Книга рецептов";
    }
}

