package com.meta.air_jet.result;

import com.meta.air_jet.Api.rank.RankClient;
import com.meta.air_jet._core.utils.SecurityUtils;
import com.meta.air_jet.user.domain.User;
import com.meta.air_jet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
        System.out.println("ResultController.save");
        String loginId = SecurityUtils.getCurrentUserId();
        User findUser = userRepository.findByLoginId(loginId);

        // api 요청 body 생성
        Map<String, Object> requestBody = Map.of(
                "start", dto.engineStart(),
                "takeoff", dto.takeOff(),
                "formation", dto.formation(),
                "air_to_ground", dto.airToGround()
        );
        // api 요청 및 response
        ResultResponseDTO.saveDTO saveDTO = rankClient.evaluateSimulation(requestBody);

        int rank = resultService.save(dto, findUser.getId(), saveDTO);

        ResultResponseDTO.responseDTO responseDTO = new ResultResponseDTO.responseDTO(rank, saveDTO.feedback());
        return ResponseEntity.ok(responseDTO);
    }

}
