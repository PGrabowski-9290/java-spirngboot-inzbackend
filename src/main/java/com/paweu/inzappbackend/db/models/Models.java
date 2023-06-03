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
    private String make;
    @Field
    private String year;
    @Field
    private String model;
    @Field
    private Collection<String> category;

    public Models(){}

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Collection<String> getCategory() {
        return category;
    }

    public void setCategory(Collection<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Models{" +
                "_id=" + _id +
                ", Make='" + make + '\'' +
                ", Year='" + year + '\'' +
                ", Model='" + model + '\'' +
                ", Category=" + category +
                '}';
    }
}
