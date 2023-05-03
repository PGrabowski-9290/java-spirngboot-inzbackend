package com.paweu.inzappbackend.settings;

import com.paweu.inzappbackend.db.models.Settings;
import com.paweu.inzappbackend.models.ReqResp.Resp;
import com.paweu.inzappbackend.settings.request.UpdateRequest;
import com.paweu.inzappbackend.settings.service.SettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<Resp<Settings>>> getGlobal(@PathVariable int id){
        return settingsService.getSettingsGlobal(id);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Resp<Settings>>> update(@RequestBody UpdateRequest updateRequest, @PathVariable int id){
        return settingsService.updateSettings(updateRequest, id);
    }
}
