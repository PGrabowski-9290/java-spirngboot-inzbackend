package com.paweu.inzappbackend.db.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "settings")
public class Settings {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;
    @Field
    private int id;
    public Company companyDetails;
    public Contact contact;
    public Person ownerDetails;

    public Settings(){}

    public Settings(ObjectId _id, int id, Company companyDetails, Contact contact, Person ownerDetails) {
        this._id = _id;
        this.id = id;
        this.companyDetails = companyDetails;
        this.contact = contact;
        this.ownerDetails = ownerDetails;
    }

    public Settings(int id, Company companyDetails, Contact contact, Person ownerDetails) {
        this.id = id;
        this.companyDetails = companyDetails;
        this.contact = contact;
        this.ownerDetails = ownerDetails;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(Company companyDetails) {
        this.companyDetails = companyDetails;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Person getOwnerDetails() {
        return ownerDetails;
    }

    public void setOwnerDetails(Person ownerDetails) {
        this.ownerDetails = ownerDetails;
    }

}
