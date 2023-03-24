package com.paweu.inzappbackend.auth;

import com.paweu.inzappbackend.auth.refresh.ReqestRefresh;
import com.paweu.inzappbackend.auth.refresh.ResponseRefresh;
import com.paweu.inzappbackend.auth.register.ResponseRegister;
import com.paweu.inzappbackend.db.IUserRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private IUserRepository userRepository;
    AuthController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/newAccount")
    public Mono<ResponseRegister> newAccount()

    @PostMapping("/test")
    public Mono<ResponseRefresh> test(@RequestBody ReqestRefresh model) {
        return Mono.just( new ResponseRefresh(model.text(), model.id()));
    }
}
