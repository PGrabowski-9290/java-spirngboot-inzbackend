package com.paweu.inzappbackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
//todo poczytaj o filtrach
//todo OnePerRequestFilter
    /**
     * Filter stworzyć security, tutaj obsługa JWT - validacja, generowanie
     */

    @Value(value = "${jwt.secretToken}")
    private String secretAccessToken;
    @Value(value = "${jwt.refreshToken}")
    private String secretRefreshToken;
    @Value("${jwt.expires.accessToken}")
    private long accessTokenExpires;
    @Value("${jwt.expires.refToken}")
    private long refTokenExpires;
    public JwtService() {
    }

    private String generate(String email, String role, long expires, Algorithm algorithm){
        return JWT.create()
                .withSubject(email)
                .withClaim("role", role)
                .withExpiresAt(new Date(new Date().getTime() + expires))
                .sign(algorithm);
    }

    public String generateAccessToken(String email, String role){
        return generate(
                email,
                role,
                accessTokenExpires,
                Algorithm.HMAC512(secretAccessToken)
        );
    }

    public String getUserEmail(String token){
        return JWT.require(Algorithm.HMAC512(secretAccessToken))
                .build()
                .verify(token)
                .getSubject();
    }

    public Boolean isValidAccess(String token, String email) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(secretAccessToken))
                .withClaimPresence("sub")
                .build()
                .verify(token);

        return decodedJWT.getSubject().equalsIgnoreCase(email);
    }

}
