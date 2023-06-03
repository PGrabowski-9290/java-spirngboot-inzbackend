package com.paweu.inzappbackend.carsModels.service;


import com.paweu.inzappbackend.db.models.Models;
import com.paweu.inzappbackend.models.ReqResp.Resp;
import com.paweu.inzappbackend.models.exception.ResponseExceptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class CarsModelsService {


    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    public Mono<ResponseEntity<Resp<List<?>>>> getList(String make, String year, String category){
        Query modelsQuery = new Query();
        modelsQuery.fields().include("model");
        if(make != null){
            Criteria makeCriteria = Criteria.where("make").is(make);
            modelsQuery.addCriteria(makeCriteria);
        }
        if(year != null){
            Criteria yearCriteria = Criteria.where("year").is(year);
            modelsQuery.addCriteria(yearCriteria);
        }
        if(category != null){
            Criteria categoryCriteria = Criteria.where("category").is(category);
            modelsQuery.addCriteria(categoryCriteria);
        }

        return mongoTemplate.findDistinct(modelsQuery, "model", "models" ,Models.class)
                .onErrorResume(throwable -> Mono.error(new ResponseExceptionModel("Błąd w odczycie danych",500)))
                .collectList()
                .flatMap(m -> Mono.just(ResponseEntity.ok().body(new Resp<>("Pobrano", m))));
    }
}
