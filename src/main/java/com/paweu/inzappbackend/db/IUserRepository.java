package com.paweu.inzappbackend.db;

import com.paweu.inzappbackend.db.models.UserModel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IUserRepository extends ReactiveMongoRepository<UserModel, String> {

    Mono<UserModel> findByEmail(String email);
}
