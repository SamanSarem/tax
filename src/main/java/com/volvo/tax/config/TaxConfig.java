package com.volvo.tax.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class TaxConfig {

    private Set<String> exempt;
    private Map<String,Integer> taxRate;

    private Set<LocalDate> publicHolidays;

    public Set<String> getExempt() {
        return exempt;
    }

    public void setExempt(Set<String> exempt) {
        this.exempt = exempt;
    }

    public Map<String, Integer> getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Map<String, Integer> taxRate) {
        this.taxRate = taxRate;
    }

    public Set<LocalDate> getPublicHolidays() {
        return publicHolidays;
    }

    public void setPublicHolidays(Set<LocalDate> publicHolidays) {
        this.publicHolidays = publicHolidays;
    }
}
