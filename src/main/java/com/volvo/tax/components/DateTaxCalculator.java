package com.volvo.tax.components;

import com.volvo.tax.config.TaxConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.JULY;

@Component
public class DateTaxCalculator {
    private final TaxConfig taxConfig;

    private Set<LocalDate> holidayAndTheDayBefore;

    @PostConstruct
    public void init(){
        holidayAndTheDayBefore=new HashSet<>();
        holidayAndTheDayBefore.addAll(taxConfig.getPublicHolidays());
        Set<LocalDate> daysBeforeHolidays = taxConfig.getPublicHolidays().stream().map(localDate -> localDate.minusDays(1)).collect(Collectors.toSet());
        daysBeforeHolidays.addAll(daysBeforeHolidays);
    }

    public DateTaxCalculator(TaxConfig taxConfig) {
        this.taxConfig = taxConfig;
    }

    private boolean isHolidayOrTheDayBefore(LocalDate date){
        return holidayAndTheDayBefore.contains(date);
    }

    private boolean isWeekend(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return (dayOfWeek.equals(SATURDAY)||dayOfWeek.equals(SUNDAY));
    }

    //I can add free months to config yml for more flexibility
    private boolean isJuly(LocalDate date){
        Month month = date.getMonth();
        return month.equals(JULY);
    }

    public boolean isFreeOfChargeDay(LocalDate date){
        return isHolidayOrTheDayBefore(date) ||
        isWeekend(date) ||
        isJuly(date);
    }
    public boolean isFreeOfChargeDay(LocalDateTime dateTime){
        return isFreeOfChargeDay(dateTime.toLocalDate());
    }



}
