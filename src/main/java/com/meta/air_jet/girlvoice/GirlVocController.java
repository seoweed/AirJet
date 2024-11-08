package com.meta.air_jet.girlvoice;

import com.meta.air_jet.manvoice.ManVoc;
import com.meta.air_jet.manvoice.ManVocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GirlVocController {

    private final GirlVocService girlVocService;

    @GetMapping("voice/girl/{id}")
    public ResponseEntity<?> getGirlVoice(@PathVariable("id") Long id) {
        GirlVoc girlVoc = girlVocService.getGirlVocById(id);
        return ResponseEntity.ok(girlVoc);
    }
}
