package com.pmarek.exchangeService.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateSeries {
    @JsonProperty("currency")
    private String currencyName;
    @JsonProperty("code")
    private String currencyCode;
    @JsonProperty("rates")
    private List<ExchangeRate> exchangeRates;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(List<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @Override
    public String toString() {
        return "ExchangeRateSeries{" +
                "currencyName='" + currencyName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", exchangeRates=" + exchangeRates +
                '}';
    }
}
