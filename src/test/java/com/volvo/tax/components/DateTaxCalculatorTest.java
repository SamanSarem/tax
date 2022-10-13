package com.volvo.tax.components;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
class DateTaxCalculatorTest {

    @Autowired
    DateTaxCalculator dateTaxCalculator;

    @Test
    void julyShouldBeFree() {
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,7,2)));
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,7,4)));
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,7,6)));
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,7,8)));
    }

    @Test
    void saturdaysShouldBeFree() {
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,2,9)));
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,2,16)));
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,2,23)));
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,5,18)));
    }

    @Test
    void sundaysShouldBeFree() {
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,2,10)));
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,2,17)));
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,2,24)));
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,5,19)));
    }


    @Test
    void swedenNationalDayAndTheDayBeforeShouldBeFree() {
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,6,6)));
        Assertions.assertTrue(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,6,5)));
    }

    @Test
    void aNormalDayShouldNotBeFree() {
        Assertions.assertFalse(dateTaxCalculator.isFreeOfChargeDay(LocalDate.of(2013,9,18)));
    }

}