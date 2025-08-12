package com.pe.demo.controller;

import com.pe.demo.api.ExchangeRateApi;
import com.pe.demo.model.ResponseExchangeRate;
import com.pe.demo.service.ExchangeRateService;


public class ExchangeRateController implements ExchangeRateApi {

 private final ExchangeRateService exchangeRateService;

  public ExchangeRateController(ExchangeRateService exchangeRateService) {
    this.exchangeRateService = exchangeRateService;
  }

  @Override
  public ResponseExchangeRate exchangeGet(String dni) {
    return exchangeRateService.getExchangeRate(dni);
  }
}
