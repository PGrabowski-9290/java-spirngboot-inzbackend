package com.paweu.inzappbackend.db.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class UserModel {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;
    @Indexed(unique = true)
    @Field
    private String email;
    private String name;
    private String role;
    @Field
    private Boolean isSuperAdmin;
    private String password;
    private String refreshToken;
    @Field
    private Boolean isActive;


    public UserModel(){}

    public UserModel(String email, String name, String role){
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public UserModel(String email, String name, String role, String password){
        this.email = email;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Boolean getActive() {
        return this.isActive != null ? this.isActive : false;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}