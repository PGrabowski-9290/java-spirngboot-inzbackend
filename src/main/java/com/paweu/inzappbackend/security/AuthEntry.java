package com.paweu.inzappbackend.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class AuthEntry implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        System.out.println("auth entry error");
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        ResponseExceptionModel respEx = new ResponseExceptionModel(ex.getMessage(), 401);
//        return Mono.error(respEx);
        return null;
    }
}
