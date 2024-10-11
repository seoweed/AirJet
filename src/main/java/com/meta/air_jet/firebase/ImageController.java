package com.meta.air_jet.firebase;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {
    private final FireBaseService fireBaseService;

    @PostMapping("/image/save")
    public String saveImage(@RequestParam("image") MultipartFile image) {
        try {
            return fireBaseService.uploadImage(image);
        } catch (IOException e) {
            e.printStackTrace();
            return "이미지 업로드 실패";
        }
    }
}
