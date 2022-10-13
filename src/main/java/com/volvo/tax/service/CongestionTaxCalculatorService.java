package com.volvo.tax.service;

import com.volvo.tax.components.DateTaxCalculator;
import com.volvo.tax.components.TimeTaxCalculator;
import com.volvo.tax.components.VehicleTaxCalculator;
import com.volvo.tax.model.Vehicle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CongestionTaxCalculatorService {

    private DateTaxCalculator dateTaxCalculator;
    private VehicleTaxCalculator vehicleTaxCalculator;
    private TimeTaxCalculator timeTaxCalculator;

    public CongestionTaxCalculatorService(DateTaxCalculator dateTaxCalculator, VehicleTaxCalculator vehicleTaxCalculator, TimeTaxCalculator timeTaxCalculator) {
        this.dateTaxCalculator = dateTaxCalculator;
        this.vehicleTaxCalculator = vehicleTaxCalculator;
        this.timeTaxCalculator = timeTaxCalculator;
    }

    public int getTollFee(LocalDateTime localDateTime) {
        return timeTaxCalculator.getTax(localDateTime);
    }

    public Integer getTax(Vehicle vehicle, List<LocalDateTime> dates) {
        if (vehicleTaxCalculator.isExempt(vehicle)) {
            return 0;
        }
        Map<LocalDate, List<LocalDateTime>> dailyReport =toSortedDaily(dates);
        return dailyReport.entrySet().stream()
                .filter(localDateListEntry -> !dateTaxCalculator.isFreeOfChargeDay(localDateListEntry.getKey()))
                .map(localDateListEntry -> calculateDailyTax(localDateListEntry.getValue()))
                .reduce(0, (a, b) -> a + b);
    }
    private Map<LocalDate, List<LocalDateTime>> toSortedDaily(List<LocalDateTime> dates){
        Map<LocalDate, List<LocalDateTime>> dailyReport =
                new HashMap<>();
        dates.stream().sorted().forEach(localDateTime -> {
            LocalDate localdate = localDateTime.toLocalDate();
            dailyReport.putIfAbsent(localdate, new ArrayList<>());
            dailyReport.get(localdate).add(localDateTime);
        });
        return dailyReport;
    }

    public Integer calculateDailyTax(List<LocalDateTime> localDateTimes) {
        LocalDateTime startOfTimeWindow = null;
        Integer certainTax = 0;
        Integer uncertainTax = 0;

        for (LocalDateTime dateTime : localDateTimes
        ) {//TODO It's possible to break the loop for better performance in 60 SEK
            if (startOfTimeWindow == null) {
                startOfTimeWindow = dateTime;
                uncertainTax = getTollFee(dateTime);
            } else if (ChronoUnit.MINUTES.between(startOfTimeWindow, dateTime) < 60) {
                var fee = getTollFee(dateTime);
                if (fee > uncertainTax) {
                    uncertainTax = fee;
                }
            } else {
                startOfTimeWindow = dateTime;
                certainTax += uncertainTax;
                uncertainTax = getTollFee(dateTime);
            }
        }
        var result = certainTax + uncertainTax;
        if (result < 60) {
            return result;
        }
        return 60;
    }

}