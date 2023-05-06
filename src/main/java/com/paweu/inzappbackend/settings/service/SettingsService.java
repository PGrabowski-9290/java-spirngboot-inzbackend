package com.paweu.inzappbackend.settings.service;

import com.paweu.inzappbackend.db.ISettingsRepository;
import com.paweu.inzappbackend.db.models.Settings;
import com.paweu.inzappbackend.models.ReqResp.Resp;
import com.paweu.inzappbackend.models.exception.ResponseExceptionModel;
import com.paweu.inzappbackend.settings.request.UpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SettingsService {
    private final ISettingsRepository settingsRepository;

    public SettingsService(ISettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Mono<ResponseEntity<Resp<Settings>>>  getSettingsGlobal(int id){
        return settingsRepository.findSettingsById(id)
                .onErrorResume(throwable -> Mono.error(new ResponseExceptionModel("Cos nie pyklo",400)))
                .switchIfEmpty(Mono.error(new ResponseExceptionModel("nie ma",404)))
                .flatMap(settings -> {
                    System.out.println(settings == null);
                    return Mono.just(ResponseEntity.ok().body(new Resp<Settings>("Success", settings)));
                });
    }

    public Mono<ResponseEntity<Resp<Settings>>> updateSettings(UpdateRequest updateRequest, int idParam){
        return settingsRepository.findSettingsById(idParam)
                .onErrorResume(throwable -> Mono.error(new ResponseExceptionModel("Cos nie pyklo", 400)))
                .switchIfEmpty(Mono.error(new ResponseExceptionModel("Brak obiektu ustawien", 404)))
                .flatMap(settings -> {
                   settings.companyDetails.setCity(updateRequest.getCity());
                   settings.companyDetails.setName(updateRequest.getCompanyName());
                   settings.companyDetails.setNip(updateRequest.getNip());
                   settings.companyDetails.setStreet(updateRequest.getStreet());
                   settings.companyDetails.setZipCode(updateRequest.getZipCode());

                   settings.ownerDetails.setFirstName(updateRequest.getFirstName());
                   settings.ownerDetails.setSurName(updateRequest.getSurName());

                   settings.contact.setEmail(updateRequest.getEmail());
                   settings.contact.setPhoneNumber(updateRequest.getPhone());

                   return  settingsRepository.save(settings)
                           .onErrorResume(throwable -> Mono.error(new ResponseExceptionModel("Błąd zapisu do bazy", 500)))
                           .thenReturn(ResponseEntity.ok().body(new Resp<Settings>("Success", settings)));
                });
    }
}
