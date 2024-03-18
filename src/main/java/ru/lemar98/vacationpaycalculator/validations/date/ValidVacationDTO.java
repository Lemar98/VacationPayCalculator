package ru.lemar98.vacationpaycalculator.validations.date;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VacationDateRangeValidator.class)
@Documented
public @interface ValidVacationDTO {
    String message() default "Some validates failed. Try again";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
