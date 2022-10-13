package com.volvo.tax.components;

import com.volvo.tax.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class VehicleTaxCalculatorTest {

    @Autowired
    VehicleTaxCalculator vehicleTaxCalculator;

    @Test
    void isAnyVehicleShouldPayTaxExceptExemptList() {
        Assertions.assertFalse(vehicleTaxCalculator.isExempt(new Vehicle("AnyVehicle")));
    }

    @Test
    void exemptListShouldNotPayTax() {
        Assertions.assertTrue(vehicleTaxCalculator.isExempt(new Vehicle("Emergency vehicles")));
        Assertions.assertTrue(vehicleTaxCalculator.isExempt(new Vehicle("Busses")));
        Assertions.assertTrue(vehicleTaxCalculator.isExempt(new Vehicle("Diplomat vehicles")));
        Assertions.assertTrue(vehicleTaxCalculator.isExempt(new Vehicle("Motorcycles")));
        Assertions.assertTrue(vehicleTaxCalculator.isExempt(new Vehicle("Military vehicles")));
        Assertions.assertTrue(vehicleTaxCalculator.isExempt(new Vehicle("Foreign vehicles")));
    }
}