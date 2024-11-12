package com.meta.air_jet.result;

import com.meta.air_jet.Api.rank.RankClient;
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

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResultController {

    private final ResultService resultService;
    private final UserRepository userRepository;
    private final RankClient rankClient;

    @PostMapping("/result")
    public ResponseEntity<?> save(@RequestBody ResultRequestDTO.saveDTO dto) {
        String loginId = SecurityUtils.getCurrentUserId();
        User findUser = userRepository.findByLoginId(loginId);


        Map<String, Object> requestBody = Map.of(
                "engineStart", dto.engineStart(),
                "taskOff", dto.takeOff(),
                "formation", dto.formation(),
                "airToGround", dto.airToGround()
        );
        ResultResponseDTO.saveDTO resultDTO = rankClient.evaluateSimulation(requestBody);
        resultService.save(dto, findUser.getId(), resultDTO);
        return ResponseEntity.ok(resultDTO);
    }

}
