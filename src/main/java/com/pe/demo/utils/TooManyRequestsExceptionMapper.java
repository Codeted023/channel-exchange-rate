package com.pe.demo.utils;

import com.pe.demo.model.Error;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TooManyRequestsExceptionMapper implements ExceptionMapper<TooManyRequestsException> {
  @Override
  public Response toResponse(TooManyRequestsException exception) {

    Error error = new Error();
    error.setCode(Response.Status.TOO_MANY_REQUESTS.toString());
    error.setMessage(exception.getMessage());

    return Response.status(Response.Status.TOO_MANY_REQUESTS)
            .entity(error)
            .type(MediaType.APPLICATION_JSON)
            .build();
  }
}