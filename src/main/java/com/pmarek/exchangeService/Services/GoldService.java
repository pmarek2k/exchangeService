package com.pmarek.exchangeService.Services;

import com.pmarek.exchangeService.Exceptions.RestTemplateResponseErrorHandler;
import com.pmarek.exchangeService.Models.Gold;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class GoldService {

    private final RestTemplate restTemplate;
    private final static Logger logger = LogManager.getLogger(CurrencyService.class);

    @Autowired
    public GoldService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }


    public double getAverageGoldPriceForLastBusinessDays(int numberOfDays){
        String url = "http://api.nbp.pl/api/cenyzlota/last/{topCount}";
        List<Gold> goldList;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);

        logger.info("Sending call to NBP gold price API");
        ResponseEntity<Gold[]> response = this.restTemplate.exchange(url, HttpMethod.GET, request, Gold[].class, numberOfDays);

        goldList = Arrays.asList(Objects.requireNonNull(response.getBody()));

        return goldList.stream().mapToDouble(Gold::getPrice).average().orElse(Double.NaN);
    }
}
