package me.roman.app.services;

import me.roman.app.services.impl.AppService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class AppServiceImpl implements AppService {
    private static final int SALARY = 100_000;
    @Override
    public int getDailyBudget() {
        return SALARY / 30;
    }

    @Override
    public int getBalance() {
        return SALARY - LocalDate.now().getDayOfMonth() * getDailyBudget();
    }
}
