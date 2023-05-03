package com.paweu.inzappbackend.db.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;

@Document(collection = "models")
public class Models {
    @Id
    private ObjectId _id;
    @Field
    private String Make;
    @Field
    private String Year;
    @Field
    private String Model;
    @Field
    private Collection<String> Category;

    public Models(){}

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Collection<String> getCategory() {
        return Category;
    }

    public void setCategory(Collection<String> category) {
        Category = category;
    }
}
