package com.pe.demo.service;

import com.pe.demo.entity.ExchangeRate;
import com.pe.demo.model.ResponseExchangeRate;
import com.pe.demo.proxy.ExchangeProxy;
import com.pe.demo.proxy.NetApi;
import com.pe.demo.repository.ExchangeRateRepository;
import com.pe.demo.service.impl.ExchangeRateServiceImpl;
import com.pe.demo.utils.TooManyRequestsException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
class ExchangeRateServiceTest {

  @InjectMock
  ExchangeRateRepository exchangeRateRepository;

  @InjectMock
  @RestClient
  NetApi netApi;

  @Inject
  ExchangeRateServiceImpl exchangeRateService;

  @Test
  void whenCountLessTenReturnResponse() {
    // Arrange
    String dni = "12345678";
    String today = java.time.LocalDate.now().toString();

    when(exchangeRateRepository.countByDateAndDni(today, dni)).thenReturn(5L);

    var fakeProxy = new ExchangeProxy();

    when(netApi.getExchangeRate()).thenReturn(fakeProxy);

    // Act
    ResponseExchangeRate response = exchangeRateService.getExchangeRate(dni);

    // Assert
    Assertions.assertNotNull(response);
    verify(exchangeRateRepository).persist((ExchangeRate) any());
    verify(netApi).getExchangeRate();
  }

  @Test
  void whenCountIsTenReturnThrowException() {
    // Arrange
    String dni = "87654321";
    String today = java.time.LocalDate.now().toString();

    when(exchangeRateRepository.countByDateAndDni(today, dni)).thenReturn(10L);

    // Act & Assert
    Assertions.assertThrows(TooManyRequestsException.class, () -> {
      exchangeRateService.getExchangeRate(dni);
    });

    verify(exchangeRateRepository, never()).persist((ExchangeRate) any());
    verify(netApi, never()).getExchangeRate();
  }
}