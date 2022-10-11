package com.volvo.tax.controller;

import com.volvo.tax.dto.TaxCalculationRequest;
import com.volvo.tax.model.Vehicle;
import com.volvo.tax.service.CongestionTaxCalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxController {

    private CongestionTaxCalculatorService taxCalculatorService;

    public TaxController(CongestionTaxCalculatorService taxCalculatorService) {
        this.taxCalculatorService = taxCalculatorService;
    }

    @PostMapping("calculate")
    public Integer calculateTax(@RequestBody TaxCalculationRequest taxCalculationRequest){
        return taxCalculatorService.getTax(new Vehicle(taxCalculationRequest.getVehicleType()),taxCalculationRequest.getCaptureTimes());
    }
    @GetMapping
    public String ping(){
        return "pong";
    }
}
