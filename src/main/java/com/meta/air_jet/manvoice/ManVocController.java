package com.meta.air_jet.manvoice;

import com.meta.air_jet._core.file.AwsFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManVocController {

    private final ManVocService manVocService;
    private final AwsFileService awsFileService;

    @PostMapping("/voice")
    public ResponseEntity<?> getManVoice(@RequestBody ManVocRequestDTO dto) throws IOException {
        System.out.println("ManVocController.getManVoice 실행");
//        ManVoc manVoc = manVocService.getManVocById(dto.getId());
//        ManVocResponseDTO manVocResponseDTO = new ManVocResponseDTO(manVoc.getVoice(), manVoc.getDescription());

        try {
            ClassPathResource audio = new ClassPathResource("audio/12628B134C5A460B4B.wav");
            byte[] bytes = Files.readAllBytes(audio.getFile().toPath());
            String testAudioEncoding = Base64.getEncoder().encodeToString(bytes);
            ManVocResponseDTO manVocResponseDTOTest = new ManVocResponseDTO(testAudioEncoding, "test 설명");
            return ResponseEntity.ok(manVocResponseDTOTest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/voice/save")
    public ResponseEntity<?> saveVoice(@RequestParam("file") MultipartFile file,
                                       @RequestParam("description") String description) throws IOException {
        String url = awsFileService.upload(file, "sound");
        manVocService.saveVoice(url, description);
        return ResponseEntity.ok(url);
    }
}
