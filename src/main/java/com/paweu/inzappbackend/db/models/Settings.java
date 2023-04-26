package com.paweu.inzappbackend.db.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "settings")
public class Settings {

    @Id
    private ObjectId _id;
    @Field
    private int id;
    private Company companyDetails;
    private Contact contact;
    private Person ownerDetails;

    public Settings(){}

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
