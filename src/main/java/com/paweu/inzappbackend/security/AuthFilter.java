package com.paweu.inzappbackend.security;

import com.paweu.inzappbackend.service.DbUsersService;
import com.paweu.inzappbackend.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements WebFilter {
    private final JwtService jwtService;
    private final DbUsersService dbUsersService;


    public AuthFilter(JwtService jwtService, DbUsersService dbUsersService){
        this.jwtService = jwtService;
        this.dbUsersService = dbUsersService;
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            String email = jwtService.getUserEmail(getJwtString(getAuthHeader(exchange)));
            Mono<DbUserDetails> user = dbUsersService.getUser(email).mapNotNull(DbUserDetails::new);

            return user.flatMap(u-> {
               if (u.isEnabled()){
                   var auth = new UsernamePasswordAuthenticationToken(u.getUsername(), null, u.getAuthorities());
                   return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
               }

                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.clearContext());
            });
        } catch (Exception e) {
            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.clearContext());
        }
    }

    private String getAuthHeader(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    }

    private String getJwtString(String headerVal){
        return headerVal.substring(7);
    }
}
