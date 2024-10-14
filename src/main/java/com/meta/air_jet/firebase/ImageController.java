package com.meta.air_jet.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
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

    @GetMapping("/image/{fileName}")
    public ResponseEntity<?> sendImage(@PathVariable("fileName") String fileName) {
        System.out.println("fileName = " + fileName);
        Blob blob = StorageClient.getInstance().bucket().get(fileName);
        System.out.println("blob = " + blob);
        byte[] content = blob.getContent();
        ByteArrayResource resource = new ByteArrayResource(content);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "image/png");
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
