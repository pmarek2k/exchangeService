package com.pmarek.exchangeService.Exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    final static Logger logger = LogManager.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            logger.error("Http server error\nCode: ", httpResponse.getStatusCode());
        } else if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            logger.error("Http client error\n", httpResponse.getStatusCode());
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.error("CODE: 404 NOT FOUND");
            }
            else if(httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST){
                logger.error("CODE: 400 BAD REQUEST");
            }
        }
    }
}