package com.paweu.inzappbackend.auth;

import com.paweu.inzappbackend.auth.test.ReqestTest;
import com.paweu.inzappbackend.auth.test.ResponseTest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/test")
    public Mono<ResponseTest> test(@RequestBody ReqestTest model) {
        return Mono.just( new ResponseTest(model.text(), model.id()));
    }
}
