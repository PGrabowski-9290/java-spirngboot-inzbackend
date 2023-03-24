package com.paweu.inzappbackend.db.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record UserModel (
        @Id
        ObjectId _id,
        @Indexed(unique = true)
        String email,
        String name,
        String role,
        Boolean isSuperAdmin,
        String password,
        String refreshToken,
        Boolean isActive
    ){}