package com.paweu.inzappbackend.auth.register;

public record RequestRegister(String email,
                              String password,
                              String role,
                              String name,
                              Boolean isSuperAdmin) {
}
