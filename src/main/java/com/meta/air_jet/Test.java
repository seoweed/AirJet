package com.meta.air_jet;

import com.meta.air_jet._core.file.AwsFileService;
import com.meta.air_jet._core.utils.ApiUtils;
import com.meta.air_jet._core.utils.SecurityUtils;
import com.meta.air_jet.map.domain.dto.MapRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class Test {
    private final AwsFileService awsFileService;

    @PostMapping("/api/test/s3/save")
    public ResponseEntity<?> s3Save(@RequestParam("file") MultipartFile file) {
        System.out.println("Test.s3Save");
        System.out.println("file = " + file);
        String url = awsFileService.upload(file, "sound");
        return ResponseEntity.ok(url);
    }

    @PostMapping("/api/test")

    public ResponseEntity<?> test() {

        System.out.println("Test.test");
        return ResponseEntity.ok(ApiUtils.success("테스트 api에 접속 성공하였습니다."));
    }

    @PostMapping("/api/upload")
    public void upload(@RequestPart("file") MultipartFile file, @RequestPart("data") MapRequestDTO.mapCreateDTO dto) throws IOException {
        System.out.println("file = " + file.toString());
        System.out.println("file = " + file.getOriginalFilename());
        System.out.println("dto = " + dto.toString());
    }

    @PostMapping("/api/test/user")
    public ResponseEntity<?> getCurrentMemberIdTest() {
        System.out.println("Test.test");
        System.out.println("로그인 한 아이디: " + SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(ApiUtils.success("테스트 api에 접속 성공하였습니다. 로그인 아이디: " + SecurityUtils.getCurrentUserId()));
    }

    @PostMapping("test")
    public ResponseEntity<?> test2(
            @RequestPart("dto") TestRequestDTO.mapDTO mapDTO,
            @RequestPart("file") MultipartFile file
    ) {
        System.out.println("Received MapDTO: " + mapDTO);

        // 이미지 파일 확인
        System.out.println("Received File Name: " + file.getOriginalFilename());
        System.out.println("Received File Size: " + file.getSize());
        return ResponseEntity.ok(mapDTO);
    }


}
