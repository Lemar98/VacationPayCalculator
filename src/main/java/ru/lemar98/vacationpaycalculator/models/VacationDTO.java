package ru.lemar98.vacationpaycalculator.models;

import org.springframework.format.annotation.DateTimeFormat;
import ru.lemar98.vacationpaycalculator.validations.date.ValidVacationDTO;

import java.time.LocalDate;
import java.util.Optional;

@ValidVacationDTO
public class VacationDTO {
    private final double averageSalary;
    private final int vacationDays;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate endDate;

    public VacationDTO() {
        this.averageSalary = 0.0;
        this.vacationDays = 0;
        this.startDate = null;
        this.endDate = null;
    }

    public VacationDTO(double averageSalary, int vacationDays, LocalDate startDate, LocalDate endDate) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public VacationDTO(double averageSalary, int vacationDays) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
        this.startDate = null;
        this.endDate = null;
    }


    public double getAverageSalary() {
        return averageSalary;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public Optional<LocalDate> getStartDate() {
        return Optional.ofNullable(startDate);
    }

    public Optional<LocalDate> getEndDate() {
        return Optional.ofNullable(endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VacationDTO that = (VacationDTO) o;

        if (Double.compare(getAverageSalary(), that.getAverageSalary()) != 0) return false;
        if (getVacationDays() != that.getVacationDays()) return false;
        if (!getStartDate().equals(that.getStartDate())) return false;
        return getEndDate().equals(that.getEndDate());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getAverageSalary());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + getVacationDays();
        result = 31 * result + (getStartDate().isPresent() ? getStartDate().hashCode() : 0);
        result = 31 * result + (getEndDate().isPresent() ? getEndDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VacationDTO{" +
                "averageSalary=" + averageSalary +
                ", vacationDays=" + vacationDays +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
