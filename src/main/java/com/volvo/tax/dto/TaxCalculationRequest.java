package com.volvo.tax.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TaxCalculationRequest {
    private String vehicleType;
    private List<LocalDateTime> captureTimes;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<LocalDateTime> getCaptureTimes() {
        return captureTimes;
    }

    public void setCaptureTimes(List<LocalDateTime> captureTimes) {
        this.captureTimes = captureTimes;
    }
}
