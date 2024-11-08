package com.meta.air_jet.manvoice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManVocController {

    private final ManVocService manVocService;

    @GetMapping("voice/man/{id}")
    public ResponseEntity<?> getManVoice(@PathVariable("id") Long id) {
        ManVoc manVoc = manVocService.getManVocById(id);
        return ResponseEntity.ok(manVoc);
    }
}
