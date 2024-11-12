package com.meta.air_jet.manvoice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManVocController {

    private final ManVocService manVocService;

    @PostMapping("voice/man")
    public ResponseEntity<?> getManVoice(@RequestBody ManVocRequestDTO dto) {
        ManVoc manVoc = manVocService.getManVocById(dto.getId());
        return ResponseEntity.ok(manVoc);
    }
}
