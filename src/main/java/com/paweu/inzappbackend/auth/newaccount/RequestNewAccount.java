package com.paweu.inzappbackend.auth.newaccount;

public record RequestNewAccount(
        String email,
        String password,
        String role,
        String name,
        Boolean isActive,
        Boolean isSuperAdmin
) {}
