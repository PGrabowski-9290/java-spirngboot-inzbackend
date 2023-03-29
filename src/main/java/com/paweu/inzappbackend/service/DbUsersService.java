package com.paweu.inzappbackend.service;

import com.paweu.inzappbackend.db.IUserRepository;
import com.paweu.inzappbackend.db.models.UserModel;
import com.paweu.inzappbackend.models.dto.UserDto;
import com.paweu.inzappbackend.models.exception.ResponseExceptionModel;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class DbUsersService {
    private final IUserRepository userRepository;

    public DbUsersService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserDto> getUser(String email){
        System.out.println("@@@@@@@@@@email: "+ email);
        return userRepository.findByEmail(email)
                .map(UserDto::new)
                .switchIfEmpty(Mono.error(Exception::new));
    }

}
