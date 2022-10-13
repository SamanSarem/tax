package com.volvo.tax.service;

import com.volvo.tax.components.DateTaxCalculator;
import com.volvo.tax.components.TimeTaxCalculator;
import com.volvo.tax.components.VehicleTaxCalculator;
import com.volvo.tax.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CongestionTaxCalculatorServiceTest {

    @Autowired
    CongestionTaxCalculatorService taxCalculatorService;

    @MockBean
    private DateTaxCalculator dateTaxCalculator;
    @MockBean
    private VehicleTaxCalculator vehicleTaxCalculator;
    @MockBean
    private TimeTaxCalculator timeTaxCalculator;


    @Test
    void eventBetweenOneHourShouldBeReduceToOne() {
        Mockito.when(timeTaxCalculator.getTax(Mockito.any(LocalDateTime.class)))
                .thenReturn(5);
        List<LocalDateTime> timeList=new ArrayList<>();
        timeList.add(LocalDateTime.of(2013,9,18,6,0));
        timeList.add(LocalDateTime.of(2013,9,18,6,10));
        timeList.add(LocalDateTime.of(2013,9,18,6,59));
        Assertions.assertEquals(5,taxCalculatorService.calculateDailyTax(timeList));
    }

    @Test
    void eventBetweenMoreThanOneHourShouldNotBeReduceToOne() {
        Mockito.when(timeTaxCalculator.getTax(Mockito.any(LocalDateTime.class)))
                .thenReturn(5);
        List<LocalDateTime> list=new ArrayList<>();
        list.add(LocalDateTime.of(2013,9,18,6,10));
        list.add(LocalDateTime.of(2013,9,18,6,29));
        list.add(LocalDateTime.of(2013,9,18,7,11));
        Assertions.assertEquals(10,taxCalculatorService.calculateDailyTax(list));
    }

    @Test
    void eventBetweenOneHourShouldBeReduceToOneMajor() {
        Mockito.when(timeTaxCalculator.getTax(Mockito.any(LocalDateTime.class)))
                .thenReturn(5);
        Mockito.when(timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,6,59)))
                .thenReturn(7);
        List<LocalDateTime> timeList=new ArrayList<>();
        timeList.add(LocalDateTime.of(2013,9,18,6,0));
        timeList.add(LocalDateTime.of(2013,9,18,6,10));
        timeList.add(LocalDateTime.of(2013,9,18,6,59));
        Assertions.assertEquals(7,taxCalculatorService.calculateDailyTax(timeList));
    }

    @Test
    void taxLessThan60ShouldAccumulate() {
        Mockito.when(timeTaxCalculator.getTax(Mockito.any(LocalDateTime.class)))
                .thenReturn(19);
        List<LocalDateTime> list=new ArrayList<>();
        list.add(LocalDateTime.of(2013,9,18,6,0));
        list.add(LocalDateTime.of(2013,9,18,8,10));
        list.add(LocalDateTime.of(2013,9,18,10,59));
        Assertions.assertEquals(57,taxCalculatorService.calculateDailyTax(list));
    }
    @Test
    void taxMoreThan60ShouldDeductTo60() {
        Mockito.when(timeTaxCalculator.getTax(Mockito.any(LocalDateTime.class)))
                .thenReturn(19);
        List<LocalDateTime> timeList=new ArrayList<>();
        timeList.add(LocalDateTime.of(2013,9,18,6,0));
        timeList.add(LocalDateTime.of(2013,9,18,8,10));
        timeList.add(LocalDateTime.of(2013,9,18,10,59));
        timeList.add(LocalDateTime.of(2013,9,18,12,59));
        Assertions.assertEquals(60,taxCalculatorService.calculateDailyTax(timeList));
    }

    @Test
    void ifAVehicleIsExemptShouldNotPayTax() {
        Mockito.when(vehicleTaxCalculator.isExempt(Mockito.any(Vehicle.class)))
                .thenReturn(true);
        Vehicle freeToPassVehicle=new Vehicle("FreeToPass");
        List<LocalDateTime> timeList=new ArrayList<>();
        timeList.add(LocalDateTime.of(2013,9,18,6,0));
        timeList.add(LocalDateTime.of(2013,9,18,8,10));
        timeList.add(LocalDateTime.of(2013,9,18,10,59));
        timeList.add(LocalDateTime.of(2013,9,18,12,59));
        Assertions.assertEquals(0,taxCalculatorService.getTax(freeToPassVehicle,timeList));
    }



}