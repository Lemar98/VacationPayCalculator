package ru.lemar98.vacationpaycalculator.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lemar98.vacationpaycalculator.models.VacationDTO;
import ru.lemar98.vacationpaycalculator.services.VacationCalculatorService;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
public class VacationCalculatorController {

    private final VacationCalculatorService vacationCalculatorService;
    private final Logger logger = LoggerFactory.getLogger("CalculatorController");

    public VacationCalculatorController(VacationCalculatorService vacationCalculatorService) {
        this.vacationCalculatorService = vacationCalculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculateVacationPay(@Valid @RequestBody VacationDTO vacationDTO) {
        logger.info("Received get request to calculate with data: {}", vacationDTO);
        double averageSalary = vacationDTO.getAverageSalary();
        int vacationDays = vacationDTO.getVacationDays();
        LocalDate startDate = vacationDTO.getStartDate().orElse(null);
        LocalDate endDate = vacationDTO.getEndDate().orElse(null);

        double sumToPay = calculateSumToPay(averageSalary, vacationDays, startDate, endDate);
        logger.info("Calculated sum for dto: {} is {}", vacationDTO, sumToPay);

        return ResponseEntity.ok(sumToPay);
    }

    private double calculateSumToPay(double averageSalary, int vacationDays, LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return vacationCalculatorService.calculate(averageSalary, vacationDays);
        } else {
            return vacationCalculatorService.calculateWithStartDate(averageSalary, startDate, endDate);
        }
    }
}
