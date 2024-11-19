package com.meta.air_jet.girlvoice;

import com.meta.air_jet._core.file.AwsFileService;
import com.meta.air_jet.manvoice.ManVoc;
import com.meta.air_jet.manvoice.ManVocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GirlVocController {

    private final GirlVocService girlVocService;
    private final AwsFileService awsFileService;

    @PostMapping("voice/girl")
    public ResponseEntity<?> getGirlVoice(@RequestBody GirlVocRequestDTO dto) {
        GirlVoc girlVoc = girlVocService.getGirlVocById(dto.getId());
        GirlVocResponseDTO girlVocResponseDTO = new GirlVocResponseDTO(girlVoc.getVoice(), girlVoc.getDescription());
        return ResponseEntity.ok(girlVocResponseDTO);
    }

    @PostMapping("/g-voice/save")
    public ResponseEntity<?> saveVoice(@RequestParam("file") MultipartFile file) {

        String url = awsFileService.uploadkorean(file, "sound");
        String[] split = file.getOriginalFilename().split("\\.");
        girlVocService.saveVoice(url, split[0]);
        return ResponseEntity.ok(url);
    }
}
