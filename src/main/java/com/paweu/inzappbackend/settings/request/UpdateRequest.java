package com.paweu.inzappbackend.settings.request;

import com.paweu.inzappbackend.db.models.Company;
import com.paweu.inzappbackend.db.models.Contact;
import com.paweu.inzappbackend.db.models.Person;
import com.paweu.inzappbackend.db.models.Settings;

public class UpdateRequest {
    private String firstName;
    private String surName;
    private String companyName;
    private String nip;
    private String city;
    private String zipCode;
    private String street;
    private String phone;
    private String email;


    public UpdateRequest(String firstName, String surName, String companyName, String nip, String city, String zipCode, String street, String phone, String email) {
        this.firstName = firstName;
        this.surName = surName;
        this.companyName = companyName;
        this.nip = nip;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getNip() {
        return nip;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Settings toSettings(int id){
        Company company = new Company(companyName, nip, city, zipCode, street);
        Contact contact = new Contact(phone, email);
        Person ownerDetails = new Person(firstName, surName);
        return new Settings(id, company, contact, ownerDetails);
    }



    @Override
    public String toString() {
        return "UpdateRequest{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", nip='" + nip + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
