package com.pmarek.exchangeService.Services;

import com.pmarek.exchangeService.Models.ExchangeRate;
import com.pmarek.exchangeService.Models.ExchangeRateSeries;
import com.pmarek.exchangeService.Exceptions.RestTemplateResponseErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    private final RestTemplate restTemplate;
    private final static Logger logger = LogManager.getLogger(CurrencyService.class);

    @Autowired
    public CurrencyService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder
        .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public ExchangeRateSeries getCurrencyExchangeRateForLastBusinessDays(String currencyCode, int numberOfDays){
        ExchangeRateSeries exchangeRateSeries;
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/{code}/last/{topCount}/";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);

        logger.info("Sending call to exchange rate NBP API");
        ResponseEntity<ExchangeRateSeries> response = this.restTemplate.exchange(url, HttpMethod.GET, request, ExchangeRateSeries.class, currencyCode, numberOfDays);

        exchangeRateSeries = response.getBody();
        exchangeRateSeries.setExchangeRates(exchangeRateSeries.getExchangeRates().stream()
                .map(exchangeRate -> new ExchangeRate(exchangeRate.getDate(), 1.0 / exchangeRate.getCurrencyRate()))
                .collect(Collectors.toList()));

        return exchangeRateSeries;
    }
}
