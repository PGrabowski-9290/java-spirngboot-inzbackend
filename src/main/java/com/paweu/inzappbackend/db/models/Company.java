package com.paweu.inzappbackend.db.models;

public class Company {
    private String name;
    private String nip;
    private String city;
    private String zipCode;
    private String street;

    public Company(String name, String nip, String city, String zipCode, String street) {
        this.name = name;
        this.nip = nip;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
    }

    public Company(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
