package com.paweu.inzappbackend.auth;

import com.paweu.inzappbackend.auth.login.RequestLogin;
import com.paweu.inzappbackend.auth.login.ResponseLogin;
import com.paweu.inzappbackend.auth.newaccount.RequestNewAccount;
import com.paweu.inzappbackend.models.ReqResp.Resp;
import com.paweu.inzappbackend.service.AuthService;
import com.paweu.inzappbackend.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/newAccount")
    public Mono<ResponseEntity<Resp<?>>> newAccount(@RequestBody RequestNewAccount user){
        return authService.register(user.email(), user.name(), user.password(),user.role());
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Resp<?>>> login(@RequestBody RequestLogin data){
        return  authService.login(data.email(), data.password());
    }
}
