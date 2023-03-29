package com.paweu.inzappbackend.models.dto;

import com.paweu.inzappbackend.db.models.UserModel;

public class UserDto {
    private String email;
    private String role;
    private String name;

    private String refreshToken = "";
    private String password;


    public UserDto(){

    }

    public UserDto(String email, String role, String name) {
        this.email = email;
        this.role = role;
        this.name = name;
    }

    public UserDto(UserModel userModel){
        this.email = userModel.getEmail();
        this.role = userModel.getRole();
        this.name = userModel.getName();
        this.refreshToken = userModel.getRefreshToken();
        this.password = userModel.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
