package com.paweu.inzappbackend.carsModels.service;

import com.paweu.inzappbackend.db.IModelsRepository;
import com.paweu.inzappbackend.db.models.Models;
import com.paweu.inzappbackend.models.ReqResp.Resp;
import com.paweu.inzappbackend.models.exception.ResponseExceptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CarsModelsService {

//    @Autowired
//    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private IModelsRepository modelsRepository;

    public Mono<ResponseEntity<Resp<List<?>>>> getList(String make, String year, String category){
        Query modelsQuery = new Query();
        Models exModel = new Models();
        modelsQuery.fields().include("Models");
        if(make != null){
            exModel.setMake(make);
            Criteria makeCriteria = Criteria.where("Make").is(make);
            modelsQuery.addCriteria(makeCriteria);
        }
        if(year != null){
            exModel.setYear(year);
            Criteria yearCriteria = Criteria.where("Year").is(year);
            modelsQuery.addCriteria(yearCriteria);
        }
        if(category != null){
            List<String> categoryList = List.of(category);
            exModel.setCategory(categoryList);
            Criteria categoryCriteria = Criteria.where("Category").is(category);
            modelsQuery.addCriteria(categoryCriteria);
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues();
        Example<Models> e = Example.of(exModel, matcher);


        return modelsRepository.findAll(e)
                .onErrorResume(throwable -> Mono.error(new ResponseExceptionModel("Błąd w odczycie danych",500)))
                .switchIfEmpty(Mono.just(new Models()))
                .map(i -> {
                    System.out.println(i.getModel());
                    return i.getModel();
                })
                .collectList()
                .flatMap(m -> Mono.just(ResponseEntity.ok().body(new Resp<>("Pobrano", m))));
    }
}
