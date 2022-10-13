package com.volvo.tax.components;

import com.volvo.tax.config.TaxConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class TimeTaxCalculator {//TODO the configuration should be place into a database hence we can easily query

    private Integer _06_00_06_29;
    private Integer _06_30_06_59;
    private Integer _07_00_07_59;
    private Integer _08_00_08_29;
    private Integer _08_30_14_59;
    private Integer _15_00_15_29;
    private Integer _15_30_16_59;
    private Integer _17_00_17_59;
    private Integer _18_00_18_29;
    private Integer _18_30_05_59;


    private final TaxConfig taxConfig;

    public TimeTaxCalculator(TaxConfig taxConfig) {
        this.taxConfig = taxConfig;
    }

    @PostConstruct
    public void init() {
        _06_00_06_29 = taxConfig.getTaxRate().get("06000629");
        _06_30_06_59 = taxConfig.getTaxRate().get("06300659");
        _07_00_07_59 = taxConfig.getTaxRate().get("07000759");
        _08_00_08_29 = taxConfig.getTaxRate().get("08000829");
        _08_30_14_59 = taxConfig.getTaxRate().get("08301459");
        _15_00_15_29 = taxConfig.getTaxRate().get("15001529");
        _15_30_16_59 = taxConfig.getTaxRate().get("15301659");
        _17_00_17_59 = taxConfig.getTaxRate().get("17001759");
        _18_00_18_29 = taxConfig.getTaxRate().get("18001829");
        _18_30_05_59 = taxConfig.getTaxRate().get("18300559");
    }

    public Integer getTax(LocalDateTime localDateTime) {
        Integer hour = localDateTime.getHour();
        Integer minute = localDateTime.getMinute();
        if (hour.equals(6)) {
            if (minute < 30) {
                return _06_00_06_29;
            } else return _06_30_06_59;
        } else if (hour.equals(7)) {
            return _07_00_07_59;
        } else if (hour >= 8 && hour < 15) {
            if (minute < 30) {
                return _08_00_08_29;
            } else return _08_30_14_59;
        } else if (hour.equals(15)) {
            if (minute < 30) {
                return _15_00_15_29;
            } else return _15_30_16_59;
        }else if (hour.equals(16)) {
            return _15_30_16_59;
        } else if (hour.equals(17)) {
            return _17_00_17_59;
        } else if (hour.equals(18)) {
            if (minute < 30) {
                return _18_00_18_29;
            } else return _18_30_05_59;
        } else return _18_30_05_59;
    }

}
