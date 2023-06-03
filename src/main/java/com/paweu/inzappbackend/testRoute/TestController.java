package com.paweu.inzappbackend.testRoute;

import com.paweu.inzappbackend.models.ReqResp.Resp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public Mono<ResponseEntity<Resp<?>>> test(){
        return Mono.just(ResponseEntity.ok().body(new Resp<>("ok","test")));
    }
}
