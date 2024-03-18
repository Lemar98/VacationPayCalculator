package ru.lemar98.vacationpaycalculator.validations.date;

import ru.lemar98.vacationpaycalculator.models.VacationDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class VacationDateRangeValidator implements ConstraintValidator<ValidVacationDTO, VacationDTO> {

    @Override
    public boolean isValid(VacationDTO value, ConstraintValidatorContext context) {
        if (value.getAverageSalary() <= 0.0D) {
            addConstraintViolation(context, "Average salary must be greater than zero.");
            return false;
        }

        boolean hasStartDate = value.getStartDate().isPresent();
        boolean hasEndDate = value.getEndDate().isPresent();

        if (value.getVacationDays() > 0 && (hasStartDate || hasEndDate)) {
            addConstraintViolation(context,
                    "If vacation days are specified, start date and end date should not be specified.");
            return false;
        }

        if (value.getVacationDays() <= 0 && !hasStartDate && !hasEndDate) {
            addConstraintViolation(context, "Vacation days must be greater than zero.");
            return false;
        }

        if (hasStartDate != hasEndDate) {
            addConstraintViolation(context, "Both start date and end date should be specified.");
            return false;
        }

        if (hasStartDate && hasEndDate) {
            LocalDate startDate = value.getStartDate().get();
            LocalDate endDate = value.getEndDate().get();
            if (startDate.isAfter(endDate)) {
                addConstraintViolation(context, "StartDate cannot be later than endDate");
                return false;
            }
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
