package com.meta.air_jet;

import com.meta.air_jet._core.utils.ApiUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @PostMapping("/api/test")
    public ResponseEntity<?> test() {
        System.out.println("Test.test");
        return ResponseEntity.ok(ApiUtils.success("테스트 api에 접속 성공하였습니다."));
    }
}
