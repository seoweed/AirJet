package com.meta.air_jet._core.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AwsFileService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // S3로 파일 업로드하기
    public String upload(MultipartFile uploadFile, String dirName) {
        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getOriginalFilename();   // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        return uploadImageUrl;
    }

    // S3로 업로드
    private String putS3(MultipartFile uploadFile, String fileName) {
        try {
            InputStream inputStream = uploadFile.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(uploadFile.getSize());
            metadata.setContentType(uploadFile.getContentType());

            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata)
            );
//                    .withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (Exception e) {
            throw new RuntimeException("S3 업로드 중 오류 발생: " + e.getMessage(), e);
        }
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }


}
