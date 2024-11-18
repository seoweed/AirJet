package com.meta.air_jet._core.utils;

public class S3UrlParser {
    public static String getBucketNameFromUrl(String url) {
        // 예: https://my-s3-bucket.s3.amazonaws.com/folder1/folder2/myfile.txt
        String[] parts = url.split("\\.");
        return parts[0].substring(8); // "https://" 제거하여 버킷 이름 추출
    }

    public static String getFileKeyFromUrl(String url) {
        // 예: https://my-s3-bucket.s3.amazonaws.com/folder1/folder2/myfile.txt
        return url.split(".com/")[1]; // ".com/" 이후의 부분을 fileKey로 추출
    }
}
