package com.paweu.inzappbackend.service;

import com.paweu.inzappbackend.db.models.UserModel;
import com.paweu.inzappbackend.models.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class DbUsersService {

    private final ReactiveMongoTemplate mongoTemplate;

    public DbUsersService(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Mono<UserDto> getUser(String email){

        return mongoTemplate.findOne(Query.query(where("email").is(email)), UserModel.class)
                .map(UserDto::new)
                .switchIfEmpty(Mono.error(Exception::new));
    }

}