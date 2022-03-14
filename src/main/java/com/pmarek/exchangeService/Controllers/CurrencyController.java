package com.pmarek.exchangeService.Controllers;

import com.pmarek.exchangeService.Models.ExchangeRateSeries;
import com.pmarek.exchangeService.Services.CurrencyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;
    private final static Logger logger = LogManager.getLogger(CurrencyController.class);

    @Autowired
    public CurrencyController(CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @GetMapping(path = "/api/exchange-rates/{currencyCode}", produces = "application/json")
    @ResponseBody
    public ExchangeRateSeries getCurrencyExchangeRateForLast5BusinessDays(@PathVariable("currencyCode") String currencyCode){
        logger.info("Exchange-rate API call has been received.");
        return currencyService.getCurrencyExchangeRateForLastBusinessDays(currencyCode, 5);
    }
}
