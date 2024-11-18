package com.meta.air_jet.girlvoice;

import com.meta.air_jet.manvoice.ManVoc;
import com.meta.air_jet.manvoice.ManVocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GirlVocController {

    private final GirlVocService girlVocService;

    @PostMapping("voice/girl")
    public ResponseEntity<?> getGirlVoice(@RequestBody GirlVocRequestDTO dto) {
        GirlVoc girlVoc = girlVocService.getGirlVocById(dto.getId());
        GirlVocResponseDTO girlVocResponseDTO = new GirlVocResponseDTO(girlVoc.getVoice(), girlVoc.getDescription());
        return ResponseEntity.ok(girlVocResponseDTO);
    }
}
