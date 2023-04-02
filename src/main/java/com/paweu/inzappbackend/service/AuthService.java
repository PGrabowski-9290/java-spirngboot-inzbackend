package com.paweu.inzappbackend.service;

import com.paweu.inzappbackend.auth.login.ResponseLogin;
import com.paweu.inzappbackend.auth.newaccount.ResponseNewAccount;
import com.paweu.inzappbackend.db.IUserRepository;
import com.paweu.inzappbackend.db.models.UserModel;
import com.paweu.inzappbackend.models.ReqResp.Resp;

import com.paweu.inzappbackend.models.exception.ResponseExceptionModel;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final IUserRepository userRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final DbUsersService dbUsersService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthService(IUserRepository userRepository, ReactiveMongoTemplate reactiveMongoTemplate, DbUsersService dbUsersService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.dbUsersService = dbUsersService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Mono<ResponseEntity<Resp<?>>> register(String email, String name, String password, String role){
        if (email.isEmpty() || name.isEmpty() || password.isEmpty() || role.isEmpty())
            return Mono.error(new ResponseExceptionModel("Brak wymaganych pól", 400));


        UserModel user = new UserModel(email,name, role);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user)
                .onErrorResume(throwable -> Mono.error(new ResponseExceptionModel("Error on save user to db",400)))
                .thenReturn(ResponseEntity.ok().body(new Resp<ResponseNewAccount>("Success", new ResponseNewAccount("Utworzono"))));

    }

    public Mono<ResponseEntity<Resp<?>>> login(String email, String password){
        if( email.isBlank() || password.isBlank()) {
            return Mono.error(new ResponseExceptionModel("Brak wymaganych pól", 400));
        }
//TODO tu się błąd nie rzuca trza poprawić
        return dbUsersService.getUser(email)
            .onErrorResume(throwable -> Mono.error(new ResponseExceptionModel("Brak użytkownika o podanym adresie", 404)))
                .handle((userDto, sink) -> {
                    if (!passwordEncoder.matches(password, userDto.getPassword())) {
                        sink.error(new ResponseExceptionModel("Hasło niepoprawne", 401));
                        return;
                    }

                    sink.next(ResponseEntity.ok().body(new Resp<ResponseLogin>("Zalogoawano",
                            new ResponseLogin("zalogowano", jwtService.generateAccessToken(userDto.getEmail(), userDto.getRole()), userDto.getRole()))));
                });
    }

}
