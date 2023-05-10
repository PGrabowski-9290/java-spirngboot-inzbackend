package com.paweu.inzappbackend.carsModels;

import com.paweu.inzappbackend.carsModels.service.CarsModelsService;
import com.paweu.inzappbackend.models.ReqResp.Resp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/models")
public class CarsModelsController {

    private final CarsModelsService carsListService;

    public CarsModelsController(CarsModelsService carsListService) {
        this.carsListService = carsListService;
    }


    @GetMapping("/get")
    public Mono<ResponseEntity<Resp<List<?>>>> getCarModels(@RequestParam("make") String make, @RequestParam(value = "year", required = false) String year,
                                                                 @RequestParam(value = "category", required = false) String category){
        return carsListService.getList(make, year, category);
    }
}
