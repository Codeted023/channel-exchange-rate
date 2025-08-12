package com.pe.demo.service;

import com.pe.demo.model.ResponseExchangeRate;

public interface ExchangeRateService {

  ResponseExchangeRate getExchangeRate(String dni);

}
