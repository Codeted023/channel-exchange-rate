package com.pe.demo.proxy;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "api-proxy")
@Path("tipo-cambio/today.json")
public interface NetApi {
    @GET
    ExchangeProxy getExchangeRate();
}
