package com.paweu.inzappbackend.auth.login;

public record ResponseLogin(String message,
                            String accessToken,
                            String role) {
}
