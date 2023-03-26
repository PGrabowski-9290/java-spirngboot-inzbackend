package com.paweu.inzappbackend.service;

import com.paweu.inzappbackend.auth.newaccount.ResponseNewAccount;
import com.paweu.inzappbackend.db.IUserRepository;
import com.paweu.inzappbackend.db.models.UserModel;
import com.paweu.inzappbackend.models.ReqResp.Resp;

import com.paweu.inzappbackend.models.exception.ResponseExceptionModel;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final IUserRepository userRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final DbUsersService dbUsersService;

    public AuthService(IUserRepository userRepository, ReactiveMongoTemplate reactiveMongoTemplate, DbUsersService dbUsersService) {
        this.userRepository = userRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.dbUsersService = dbUsersService;
    }

    public Mono<ResponseEntity<Resp<?>>> register(String email, String name, String password, String role){
        if (email.isEmpty() || name.isEmpty() || password.isEmpty() || role.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brak wymaganych pol");


        UserModel user = new UserModel(email,name, role, password);
        
        return userRepository.save(user)
                .onErrorResume(throwable -> Mono.error(new ResponseExceptionModel(throwable.getMessage(),400)))
                .thenReturn(ResponseEntity.ok().body(new Resp<ResponseNewAccount>("Success", new ResponseNewAccount("Utworzono"))));

    }

}
