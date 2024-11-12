package com.meta.air_jet.Api.rank;

import com.meta.air_jet.Api.geocode.GeocodeResponse;
import com.meta.air_jet.result.ResultResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "rankClient", url = "https://electric-gorgeous-spaniel.ngrok-free.app")
public interface RankClient {
    // 외부 API 호출 메서드
    @PostMapping("/evaluate_simulation")
    ResultResponseDTO.saveDTO evaluateSimulation(@RequestBody Map<String, Object> requestBody);
}
