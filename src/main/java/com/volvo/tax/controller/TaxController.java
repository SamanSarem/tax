package com.volvo.tax.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxController {
    @GetMapping
    public String ping(){
        return "pong";
    }
}
