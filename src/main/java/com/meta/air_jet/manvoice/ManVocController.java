package com.meta.air_jet.manvoice;

import com.meta.air_jet._core.file.AwsFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManVocController {

    private final ManVocService manVocService;
    private final AwsFileService awsFileService;

    @PostMapping("/m-voice")
    public ResponseEntity<?> getManVoice(@RequestBody VocRequestDTO dto) throws IOException {
        try {
            ManVoc manVoc = manVocService.getManVocById(dto.getId());
            String fileEncoding = awsFileService.
                    downloadAndEncodeFileFromUrl(manVoc.getVoice());
            VocResponseDTO vocResponseDTO = new VocResponseDTO(fileEncoding, manVoc.getDescription());
            return ResponseEntity.ok(vocResponseDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/m-voice/all")
    public ResponseEntity<?> getManVoiceAll() {
        List<VocAllResponseDTO> findAllVocEncoding = manVocService.getVocAll();
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("data", findAllVocEncoding);
        System.out.println("ManVocController.getManVoiceAll 메소드 실행, 모든 보이스 내보내기 " + LocalDateTime.now());
        return ResponseEntity.ok(stringObjectHashMap);
    }

    @PostMapping("/voice/save")
    public ResponseEntity<?> saveVoice(@RequestParam("file") MultipartFile file,
                                       @RequestParam("description") String description) {

        String url = awsFileService.upload(file, "sound");
        manVocService.saveVoice(url, description);
        return ResponseEntity.ok(url);
    }

    @PostMapping("/voice/test")
    public ResponseEntity<?> getManVoiceTest(@RequestBody VocRequestDTO dto){
        try {
            ManVoc manVoc = manVocService.getManVocById(dto.getId());
            ArrayList<Object> objects = awsFileService.downloadFileFromUrl(manVoc.getVoice());
            byte[] filedData = (byte[]) objects.get(0);
            String contentType = (String) objects.get(1);

            // HTTP 응답 생성
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, contentType);
            return ResponseEntity.ok().headers(headers).body(filedData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
