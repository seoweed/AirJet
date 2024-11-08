package com.meta.air_jet.result;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    // 결과 저장
    public Result save(ResultRequestDTO.saveDTO dto, Long memberId) {
        Result result = Result.builder()
                .memberId(memberId)
                .rank(0)
                .createAt(LocalDateTime.now())
                .playTime(dto.playTime())
                .engineStart(dto.engineStart())
                .takeOff(dto.takeOff())
                .formation(dto.formation())
                .airToGround(dto.airToGround())
                .build();
        return resultRepository.save(result);
    }
}
