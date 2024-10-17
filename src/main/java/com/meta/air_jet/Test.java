package com.meta.air_jet;

import com.meta.air_jet._core.utils.ApiUtils;
import com.meta.air_jet._core.utils.SecurityUtils;
import com.meta.air_jet.map.domain.dto.MapRequestDTO;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class Test {
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

}
