package ru.lemar98.vacationpaycalculator.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class VacationCalculatorServiceTest {

    @Autowired
    private VacationCalculatorService vacationCalculatorService;

    @Test
    void testCalculate() {
        double averageSalary = 28333.333D;
        int vacationDays = 14;

        double result = vacationCalculatorService.calculate(averageSalary, vacationDays);

        assertEquals(13538.11D, result);
    }

    @Test
    void testCalculateWithNegativeSalary() {
        double averageSalary = -60000.0D;
        int vacationDays = 10;

        assertEquals(0.0D, vacationCalculatorService.calculate(averageSalary, vacationDays));
    }

    @Test
    void testCalculateWithNegativeVacationDays() {
        double averageSalary = 60000.0D;
        int vacationDays = -1;

        assertEquals(0.0D, vacationCalculatorService.calculate(averageSalary, vacationDays));
    }

    @Test
    void testCalculateInDateRange() {
        double averageSalary = 50000.0D;
        LocalDate startDate = LocalDate.of(2024, 4, 22);
        LocalDate endDate = LocalDate.of(2024, 5, 5);

        double result = vacationCalculatorService.calculateWithStartDate(averageSalary, startDate, endDate);

        assertEquals(22184.3D, result);
    }

    @Test
    void testCalculateInRangeWithInvalidDates() {
        double averageSalary = 50000.0D;

        double result1 = vacationCalculatorService.calculateWithStartDate(averageSalary, null, null);
        double result2 = vacationCalculatorService.calculateWithStartDate(averageSalary, LocalDate.now(), null);
        double result3 = vacationCalculatorService.calculateWithStartDate(averageSalary, null, LocalDate.now());

        LocalDate startDate = LocalDate.of(2024, 4, 22);
        LocalDate endDate = LocalDate.of(2024, 5, 5);
        double result4 = vacationCalculatorService.calculateWithStartDate(averageSalary, endDate, startDate);

        assertEquals(0.0D, result1);
        assertEquals(0.0D, result2);
        assertEquals(0.0D, result3);
        assertEquals(0.0D, result4);
    }
}
