package com.pe.demo.proxy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeProxy {
  private String fecha;
  private double sunat;
  private double compra;
  private double venta;
}
