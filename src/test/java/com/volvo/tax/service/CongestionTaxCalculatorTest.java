package com.volvo.tax.service;

import com.volvo.tax.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CongestionTaxCalculatorTest {

    @Autowired
    private CongestionTaxCalculator congestionTaxCalculator;

    @Test
    void getTax() {
//        congestionTaxCalculator.getTax();
    }

    @Test
    void getTollFee() {
        Date date = new Date(2013,01,14, 21,00,00);
        System.out.println(getDayNumberOld(date));
        System.out.println(getDayStringOld(date,Locale.US));

        Vehicle vehicle=new Vehicle("Car");
        int fee = congestionTaxCalculator.getTollFee(date, vehicle);
        System.out.println(fee);


    }

    public int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public String getDayStringOld(Date date, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        return formatter.format(date);
    }
}