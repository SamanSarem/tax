package com.volvo.tax.components;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TimeTaxCalculatorTest {

    @Autowired
    TimeTaxCalculator timeTaxCalculator;

    @Test
    void feeFor6to6_29should_be_8_SEK() {
        Assertions.assertEquals(8,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,6,0)));
        Assertions.assertEquals(8,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,6,10)));
        Assertions.assertEquals(8,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,6,29)));
    }

    @Test
    void feeFor6_30to6_59should_be_13_SEK() {
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,6,30)));
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,6,40)));
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,6,59)));
    }

    @Test
    void feeFor7to7_59should_be_18_SEK() {
        Assertions.assertEquals(18,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,7,0)));
        Assertions.assertEquals(18,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,7,40)));
        Assertions.assertEquals(18,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,7,59)));
    }

    @Test
    void feeFor8to8_29should_be_13_SEK() {
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,8,0)));
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,8,15)));
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,8,29)));
    }

    @Test
    void feeFor8_30to14_59should_be_8_SEK() {
        Assertions.assertEquals(8,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,8,30)));
        Assertions.assertEquals(8,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,12,45)));
        Assertions.assertEquals(8,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,14,59)));
    }

    @Test
    void feeFor15to15_29should_be_13_SEK() {
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,15,0)));
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,15,15)));
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,15,29)));
    }

    @Test
    void feeFor15_30to16_59should_be_18_SEK() {
        Assertions.assertEquals(18,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,15,30)));
        Assertions.assertEquals(18,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,16,15)));
        Assertions.assertEquals(18,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,16,59)));
    }

    @Test
    void feeFor17to17_59should_be_13_SEK() {
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,17,0)));
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,17,15)));
        Assertions.assertEquals(13,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,17,59)));
    }

    @Test
    void feeFor18to18_29should_be_8_SEK() {
        Assertions.assertEquals(8,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,18,0)));
        Assertions.assertEquals(8,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,18,15)));
        Assertions.assertEquals(8,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,18,29)));
    }

    @Test
    void feeFor18_30to5_59should_be_0_SEK() {
        Assertions.assertEquals(0,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,18,30)));
        Assertions.assertEquals(0,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,19,15)));
        Assertions.assertEquals(0,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,20,15)));
        Assertions.assertEquals(0,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,21,15)));
        Assertions.assertEquals(0,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,22,15)));
        Assertions.assertEquals(0,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,23,15)));
        Assertions.assertEquals(0,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,0,15)));
        Assertions.assertEquals(0,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,1,15)));
        Assertions.assertEquals(0,timeTaxCalculator.getTax(LocalDateTime.of(2013,9,18,5,59)));
    }





}