package com.paweu.inzappbackend.errorhandling;

import com.paweu.inzappbackend.models.exception.ResponseExceptionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    //handle all errors from controllers
    @ExceptionHandler({ ResponseExceptionModel.class })
    public ResponseEntity<Object> handleResponseExceptionModel(final ResponseExceptionModel ex) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);

        final ApiError apiError = new ApiError(HttpStatus.valueOf(ex.getStatusCode()), ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), ex.getStatusCode());
    }
}
