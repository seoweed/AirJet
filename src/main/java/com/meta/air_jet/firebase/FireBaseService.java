package com.meta.air_jet.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class FireBaseService {
    public String uploadImage(MultipartFile file) throws IOException {
        // 파일 이름 생성
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // firebase storage에 파일 업로드
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.create(fileName, file.getInputStream(), file.getContentType());
        return fileName;
    }

    public String sendImage(String fileName) throws Exception {
        Blob blob = StorageClient.getInstance().bucket().get(fileName);
        byte[] content = blob.getContent();
        return Base64.getEncoder().encodeToString(content);
    }

}
