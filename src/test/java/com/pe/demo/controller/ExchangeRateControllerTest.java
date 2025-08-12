package com.pe.demo.controller;

import com.pe.demo.model.ResponseExchangeRate;
import com.pe.demo.service.ExchangeRateService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@QuarkusTest
class ExchangeRateControllerTest {

  @InjectMock
  ExchangeRateService exchangeRateService;

  @Inject
  ExchangeRateController controller;

  @Test
  void whenGetExchangeRateReturnOk() {
    // Arrange
    String dni = "12345678";
    ResponseExchangeRate mockResponse = new ResponseExchangeRate();
    mockResponse.setDni(dni);

    when(exchangeRateService.getExchangeRate(dni)).thenReturn(mockResponse);

    // Act
    ResponseExchangeRate result = controller.exchangeGet(dni);

    // Assert
    assertNotNull(result);
  }
}

