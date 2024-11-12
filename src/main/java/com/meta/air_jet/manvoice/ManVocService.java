package com.meta.air_jet.manvoice;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.meta.air_jet._core.utils.S3UrlParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Base64;

@Service
@Transactional
@RequiredArgsConstructor
public class ManVocService {
    private final ManVocRepository manVocRepository;
    private final AmazonS3Client amazonS3Client;


    public ManVoc getManVocById(Long manVoiceId) {
        return manVocRepository.findById(manVoiceId).orElseThrow();
    }

    public void saveVoice(String url, String description) {
        ManVoc manVoc = ManVoc.builder()
                .description(description)
                .voice(url).build();
        manVocRepository.save(manVoc);
    }

    public String downloadAndEncodeFileFromUrl(String fileUrl) {
        try {
            // URL에서 bucketName과 fileKey 추출
            String bucketName = S3UrlParser.getBucketNameFromUrl(fileUrl);
            String fileKey = S3UrlParser.getFileKeyFromUrl(fileUrl);

            // S3에서 파일 가져오기
            S3Object s3Object = amazonS3Client.getObject(bucketName, fileKey);
            InputStream inputStream = s3Object.getObjectContent();

            // InputStream을 ByteArray로 변환
            byte[] fileData = inputStream.readAllBytes();

            // Base64 인코딩
            return Base64.getEncoder().encodeToString(fileData);

        } catch (Exception e) {
            throw new RuntimeException("S3 파일을 다운로드하거나 Base64로 변환하는 중 오류 발생", e);
        }
    }
}
