package com.pe.demo.service.impl;

import com.pe.demo.mapper.ExchangeRateMapper;
import com.pe.demo.model.ResponseExchangeRate;
import com.pe.demo.proxy.NetApi;
import com.pe.demo.repository.ExchangeRateRepository;
import com.pe.demo.service.ExchangeRateService;
import com.pe.demo.utils.TooManyRequestsException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@ApplicationScoped
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private ExchangeRateRepository exchangeRateRepository;

  private final NetApi netApi;

  public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository,
                                 @RestClient NetApi netApi) {
    this.exchangeRateRepository = exchangeRateRepository;
    this.netApi = netApi;
  }

  @Override
  @Transactional
  public ResponseExchangeRate getExchangeRate(String dni) {
    if (dni == null || dni.length() != 8) {
      throw new BadRequestException("El DNI debe tener exactamente 8 caracteres");
    }

    var dateNow = java.time.LocalDate.now().toString();
    if (exchangeRateRepository.countByDateAndDni(dateNow, dni) < 10) {
      var apiProxy = netApi.getExchangeRate();
      exchangeRateRepository.persist(ExchangeRateMapper.INSTANCE.exchangeToMapper(apiProxy, dni));
      return ExchangeRateMapper.INSTANCE.responseExchangeToMapper(apiProxy, dni);
    } else {
      throw new TooManyRequestsException("Has superado el límite de 10 solicitudes diarias");
    }

  }
}
