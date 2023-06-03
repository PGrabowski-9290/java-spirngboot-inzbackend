package com.paweu.inzappbackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

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

    private String generate(String email, long expires, Algorithm algorithm){
        return JWT.create()
                .withSubject(email)
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

    public String generateRefreshToken(String email){
        return generate(
                email,
                refTokenExpires,
                Algorithm.HMAC512(secretRefreshToken)
        );
    }

    public String getUserEmail(String token){
        return JWT.require(Algorithm.HMAC512(secretAccessToken))
                .build()
                .verify(token)
                .getSubject();
    }

    public Boolean isValidToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(secretAccessToken))
                .withClaimPresence("sub")
                .build()
                .verify(token);

        return !decodedJWT.getSubject().isBlank();
    }

}
