package com.paweu.inzappbackend.db.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Collection;

@Document(collection = "offerts")
public class Offers {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;
    @Field
    private int number;
    private String title;
    private String description;

    private Collection<String> functionalities;
    private String price;

    private Car car;
    private boolean isActive;
    private boolean isSold;

    @DocumentReference
    private Salons salons;

    private Collection<String> gallery;

    public Offers(){}
}
