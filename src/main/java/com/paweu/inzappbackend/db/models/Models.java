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
}
