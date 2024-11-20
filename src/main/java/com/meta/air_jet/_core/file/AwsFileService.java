package com.meta.air_jet._core.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.meta.air_jet._core.utils.S3UrlParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
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

    // S3로 파일 업로드하기(한글)
    public String uploadkorean(MultipartFile uploadFile, String dirName) {
        String fileName = dirName + "/" + UUID.randomUUID();   // S3에 저장된 파일 이름
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

    // url로 인코딩 된 파일 받기
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

    // url로 파일 받기(byte)
    public ArrayList<Object> downloadFileFromUrl(String fileUrl) {
        try {
            // URL에서 bucketName과 fileKey 추출
            String bucketName = S3UrlParser.getBucketNameFromUrl(fileUrl);
            String fileKey = S3UrlParser.getFileKeyFromUrl(fileUrl);

            // S3에서 파일 가져오기
            S3Object s3Object = amazonS3Client.getObject(bucketName, fileKey);
            InputStream inputStream = s3Object.getObjectContent();
            // https://airjet-s3.s3.ap-northeast-2.amazonaws.com/sound/dcc2648d-8633-4658-8c5d-4a362e92a50818aaa.wav
            // InputStream을 ByteArray로 변환
            byte[] fileData = inputStream.readAllBytes();
            String contentType = s3Object.getObjectMetadata().getContentType();

            ArrayList<Object> objects = new ArrayList<>();
            objects.add(fileData);
            objects.add(contentType);
            return objects;


        } catch (Exception e) {
            throw new RuntimeException("S3 파일을 다운로드 중 오류 발생", e);
        }
    }


}
