package com.paweu.inzappbackend.service;

import com.paweu.inzappbackend.db.IUserRepository;
import com.paweu.inzappbackend.models.dto.UserDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DbUsersService {
    private final IUserRepository userRepository;

    public DbUsersService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserDto> getUser(String email){
        return userRepository.findByEmail(email)
                .map(UserDto::new)
                .switchIfEmpty(Mono.error(Exception::new));
    }

}