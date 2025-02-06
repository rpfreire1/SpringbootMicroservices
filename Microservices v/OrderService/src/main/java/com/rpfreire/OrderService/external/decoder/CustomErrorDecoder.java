package com.rpfreire.OrderService.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpfreire.OrderService.exception.CustomException;
import com.rpfreire.OrderService.external.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;


@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Error response: {}", response.request().url());
        log.info("Error response: {}", response.request().headers());
        try {
            ErrorResponse errorResponse
                    = objectMapper.readValue(response.body().asInputStream(),
                    ErrorResponse.class);
            log.info("Error response: {}", errorResponse);
            return new CustomException(
                    errorResponse.getMessage(),
                    errorResponse.getErrorCode(),
                    response.status()
            );
        } catch (IOException e) {
            throw new CustomException("InternDDDDal Server Error","INTERNAL_SERVER_ERROR",500);
        }

    }
}
