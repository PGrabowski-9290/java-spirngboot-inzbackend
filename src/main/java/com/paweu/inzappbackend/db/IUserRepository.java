package com.paweu.inzappbackend.db;

import com.paweu.inzappbackend.db.models.UserModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends ReactiveMongoRepository<UserModel, String> {

}
