package com.paweu.inzappbackend.service;

import com.paweu.inzappbackend.db.IUserRepository;
import com.paweu.inzappbackend.db.models.UserModel;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DbUsersService {
    private final IUserRepository userRepository;

    public DbUsersService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserModel> exists(String email){
        return userRepository.findByEmail(email);
    }
}
