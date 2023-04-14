package com.paweu.inzappbackend.security;

import com.paweu.inzappbackend.models.exception.ResponseExceptionModel;
import com.paweu.inzappbackend.service.DbUsersService;
import com.paweu.inzappbackend.service.JwtService;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

public class AuthManager implements ReactiveAuthenticationManager {

    final JwtService jwtService;
    final DbUsersService dbUsersService;

    public AuthManager(JwtService jwtService, DbUsersService dbUsersService) {
        this.jwtService = jwtService;
        this.dbUsersService = dbUsersService;
    }

    final
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(
                authentication
        )
                .cast(BearerToken.class)
                .flatMap(auth -> {
                    String userEmail = jwtService.getUserEmail(auth.getCredentials());
                    Mono<DbUserDetails> user = dbUsersService.getUser(userEmail).mapNotNull(DbUserDetails::new);

                    return user.<Authentication>flatMap(u -> {
                        if (!u.isEnabled()){
                            return Mono.error(new ResponseExceptionModel("Użytkownik nieaktywny", 401));
                        }

                        if (jwtService.isValidToken(auth.getCredentials())){
                            return Mono.just(new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword(), u.getAuthorities()));
                        }

                        return Mono.error(new ResponseExceptionModel("Błędny token", 401));
                    });
                });
    }
}
