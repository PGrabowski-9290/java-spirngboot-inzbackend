package com.paweu.inzappbackend.settings.service;

import com.paweu.inzappbackend.db.ISettingsRepository;
import com.paweu.inzappbackend.db.models.Settings;
import com.paweu.inzappbackend.models.ReqResp.Resp;
import com.paweu.inzappbackend.models.exception.ResponseExceptionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SettingsService {
    private final ISettingsRepository settingsRepository;

    public SettingsService(ISettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Mono<ResponseEntity<Resp<Settings>>>  getSettingsGlobal(){
        return settingsRepository.findFirstById(0)
                .onErrorResume(throwable -> Mono.error(new ResponseExceptionModel("Cos nie pyklo",400)))
                .switchIfEmpty(Mono.error(new ResponseExceptionModel("nie ma",404)))
                .flatMap(settings -> {
                    System.out.println(settings == null);
                    return Mono.just(ResponseEntity.ok().body(new Resp<Settings>("Success", settings)));
                });
    }
}
