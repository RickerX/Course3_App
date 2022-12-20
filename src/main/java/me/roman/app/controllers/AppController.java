package me.roman.app.controllers;

import me.roman.app.services.impl.AppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
public class AppController {
    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/daily")
    public int dailyBudget() {
        return appService.getDailyBudget();
    }
    @GetMapping("/balance")
    public int balance() {
        return appService.getBalance();
    }
}
