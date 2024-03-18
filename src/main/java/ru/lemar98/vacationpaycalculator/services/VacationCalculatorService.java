package ru.lemar98.vacationpaycalculator.services;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Service
public class VacationCalculatorService {

    private static final double DAYS_IN_MONTH = 29.3;
    private final HolidayManager holidayManager;

    public VacationCalculatorService(HolidayManager holidayManager) {
        this.holidayManager = holidayManager;
    }

    public double calculate(double averageSalary, int vacationDays) {
        if (averageSalary <= 0.0D) return 0.0D;
        if (vacationDays <= 0) return 0.0D;

        double averageDailyEarn = averageSalary / DAYS_IN_MONTH;
        double sumToPay = averageDailyEarn * vacationDays;
        return Math.floor(sumToPay * 100) / 100;
    }

    public double calculateWithStartDate(double averageSalary, LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) return 0.0D;
        if (startDate.isAfter(endDate)) return 0.0D;
        int vacationDays = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1; // plus one for including both the start and end dates in the calculation
        Set<Holiday> holidays = holidayManager.getHolidays(startDate, endDate);
        if (!holidays.isEmpty())
            vacationDays = Math.max(0, vacationDays - holidays.size());

        return calculate(averageSalary, vacationDays);
    }
}
