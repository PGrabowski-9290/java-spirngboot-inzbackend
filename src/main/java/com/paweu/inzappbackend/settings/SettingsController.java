package com.paweu.inzappbackend.settings;

import com.paweu.inzappbackend.db.models.Settings;
import com.paweu.inzappbackend.models.ReqResp.Resp;
import com.paweu.inzappbackend.settings.service.SettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping("/global")
    public Mono<ResponseEntity<Resp<Settings>>> getGlobal(){
        return settingsService.getSettingsGlobal();
    }
}
