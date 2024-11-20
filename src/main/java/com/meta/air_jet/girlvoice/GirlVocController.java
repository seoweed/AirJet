package com.meta.air_jet.girlvoice;

import com.meta.air_jet._core.file.AwsFileService;
import com.meta.air_jet.manvoice.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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

    @PostMapping("/voice")
    public ResponseEntity<?> getGirlVoice(@RequestBody VocRequestDTO dto) throws IOException {
        try {
            GirlVoc girlVoc = girlVocService.getGirlVocByIdx(dto.getId());
            String fileEncoding = awsFileService.
                    downloadAndEncodeFileFromUrl(girlVoc.getVoice());
            VocResponseDTO vocResponseDTO = new VocResponseDTO(fileEncoding, girlVoc.getDescription());
            return ResponseEntity.ok(vocResponseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("해당하는 id 값이 없습니다. 요청된 id: " + dto.getId());
        }


    }

    @PostMapping("/voice/all")
    public ResponseEntity<?> getGirlVoiceAll() {
        List<VocAllResponseDTO> findAllVocEncoding = girlVocService.getVocAll();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("data", findAllVocEncoding);
        System.out.println("GirlVocController.getGirlVoiceAll 메소드 실행, 모든 보이스 내보내기 " + LocalDateTime.now());

        return ResponseEntity.ok(stringObjectHashMap);
    }
}
