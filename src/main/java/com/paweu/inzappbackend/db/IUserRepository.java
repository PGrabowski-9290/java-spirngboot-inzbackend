package com.paweu.inzappbackend.db;

import com.paweu.inzappbackend.db.models.UserModel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface IUserRepository extends ReactiveMongoRepository<UserModel, String> {

    @Query("{email: ?0}")
    Mono<UserModel> findByEmail(String email);
}
