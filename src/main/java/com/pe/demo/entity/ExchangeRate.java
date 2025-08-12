package com.pe.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ExchangeRate {
  @Id
  @GeneratedValue
  private Long id;
  private String fecha;
  private String dni;
  private double compra;
  private double venta;
}
