package com.pmarek.exchangeService.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {
    @JsonProperty("effectiveDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonProperty("mid")
    private double currencyRate;

    public ExchangeRate(LocalDate date, double currencyRate) {
        this.date = date;
        this.currencyRate = currencyRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "date=" + date +
                ", currencyRate=" + currencyRate +
                '}';
    }
}
