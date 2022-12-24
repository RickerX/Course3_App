package me.roman.app.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping("/")
    @Tag(name = "Запуск приложения")
    public String startApp() {
        return "Приложение запущено";
    }
    @GetMapping("/info")
    @Tag(name = "Информация",description = "Информация о приложении")
    public String info() {
        return "Имя: Роман,Название проекта: 'App',Дата создания проекта: '12.12.2022',Описание проекта: 'Название: App,Функции'рецепты',Пока не знаю,Java 17'";
    }
}
