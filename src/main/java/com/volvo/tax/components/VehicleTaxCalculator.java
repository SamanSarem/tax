package com.volvo.tax.components;

import com.volvo.tax.config.TaxConfig;
import com.volvo.tax.model.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleTaxCalculator {
    private final TaxConfig taxConfig;

    public VehicleTaxCalculator(TaxConfig taxConfig) {
        this.taxConfig = taxConfig;
    }

    public boolean isExempt(Vehicle vehicle){
        return taxConfig.getExempt().contains(vehicle.getVehicleType());
    }




}
