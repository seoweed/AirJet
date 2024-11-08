package com.meta.air_jet.result;

import com.meta.air_jet._core.utils.ApiUtils;
import com.meta.air_jet._core.utils.SecurityUtils;
import com.meta.air_jet.user.domain.User;
import com.meta.air_jet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResultController {

    private final ResultService resultService;
    private final UserRepository userRepository;

    @PostMapping("/result/save")
    public ResponseEntity<?> save(@RequestBody ResultRequestDTO.saveDTO dto) {
        String loginId = SecurityUtils.getCurrentUserId();
        User findUser = userRepository.findByLoginId(loginId);
        resultService.save(dto, findUser.getId());
        return ResponseEntity.ok(ApiUtils.success("맵 저장 성공"));
    }
}
