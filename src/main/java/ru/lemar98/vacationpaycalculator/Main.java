package ru.lemar98.vacationpaycalculator;

import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static de.focus_shift.jollyday.core.HolidayCalendar.RUSSIA;
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public HolidayManager getHolidayManager() {
        return HolidayManager.getInstance(ManagerParameters.create(RUSSIA));
    }
}
