package com.pe.demo.mapper;

import com.pe.demo.entity.ExchangeRate;
import com.pe.demo.model.ResponseExchangeRate;
import com.pe.demo.proxy.ExchangeProxy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExchangeRateMapper {
  ExchangeRateMapper INSTANCE = Mappers.getMapper(ExchangeRateMapper.class);
  @Mapping(target = "dni", source = "dni")
  @Mapping(target = "fecha", source = "exchangeProxy.fecha")
  ExchangeRate exchangeToMapper(ExchangeProxy exchangeProxy, String dni);
  @Mapping(target = "dni", source = "dni")
  @Mapping(target = "date", source = "exchangeProxy.fecha")
  ResponseExchangeRate responseExchangeToMapper (ExchangeProxy exchangeProxy, String dni);
}
