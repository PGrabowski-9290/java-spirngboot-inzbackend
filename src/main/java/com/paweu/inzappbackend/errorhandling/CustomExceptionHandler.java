package com.paweu.inzappbackend.errorhandling;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.paweu.inzappbackend.models.exception.ResponseExceptionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    //handle 401
    @ExceptionHandler({TokenExpiredException.class})
    public ResponseEntity<Object> handleTokenExpired(final TokenExpiredException ex){
        logger.info("Token expired");
        final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "token wygasł", ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    //handle null exception
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Object> handleResponseNullExceptnion(final NullPointerException ex) {
        logger.error(ex.getClass().getName());
        logger.error("error", ex);

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Brak wymaganych pól", ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    //handle all errors from controllers
    @ExceptionHandler({ ResponseExceptionModel.class })
    public ResponseEntity<Object> handleResponseExceptionModel(final ResponseExceptionModel ex) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);

        final ApiError apiError = new ApiError(HttpStatus.valueOf(ex.getStatusCode()), ex.getLocalizedMessage(), ex.getMessage());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), ex.getStatusCode());
    }
}
