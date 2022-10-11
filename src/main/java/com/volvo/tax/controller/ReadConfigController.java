package com.volvo.tax.controller;

import com.volvo.tax.config.TaxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/config")
public class ReadConfigController {
    private static TaxConfig taxConfig;
    public ReadConfigController(TaxConfig taxConfig) {
        this.taxConfig = taxConfig;
    }

    @GetMapping(value = "exempts")
    public Set<String> exempts(){
        return taxConfig.getExempt();
    }
    @GetMapping(value = "rates")
    public Set<String> rates(){
        return taxConfig.getTaxRate().keySet();
    }
    @GetMapping(value = "public-holidays")
    public Set<LocalDate> publicHolidays(){
        return taxConfig.getPublicHolidays();
    }
}
