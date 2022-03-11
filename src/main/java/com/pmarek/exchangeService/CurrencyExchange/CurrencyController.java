package com.pmarek.exchangeService.CurrencyExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @GetMapping("/api/exchange-rates/{currencyCode}")
    @ResponseBody
    public String getExchangeRateForLast5BusinessDays(@PathVariable("currencyCode") String currencyCode){
        //TODO: Implement method

        return "";
    }
}
