package com.pe.demo.repository;

import com.pe.demo.entity.ExchangeRate;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;



@ApplicationScoped
public class ExchangeRateRepository implements PanacheRepository<ExchangeRate> {

  public long countByDateAndDni(String date, String dni) {
    return count("fecha = ?1 AND dni = ?2", date, dni);
  }

}
