package com.meta.air_jet.result;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    // 결과 저장
    public int save(ResultRequestDTO.saveDTO dto, Long userId, ResultResponseDTO.saveDTO saveDTO) {
        String inputRank = saveDTO.grade();
        int rank = 0;
        if (inputRank.equals("SSS")) {
            rank = 0;
        } else if (inputRank.equals("SS")) {
            rank = 1;
        } else if (inputRank.equals("S")) {
            rank = 2;
        } else if (inputRank.equals("A")) {
            rank = 3;
        } else if (inputRank.equals("B")) {
            rank = 4;
        } else if (inputRank.equals("C")) {
            rank = 5;
        } else if (inputRank.equals("F")) {
            rank = 6;
        }

        Result result = Result.builder()
                .userId(userId)
                .rank(rank)
                .comment("A")
                .createAt(LocalDateTime.now())
                .engineStart(dto.engineStart())
                .takeOff(dto.takeOff())
                .formation(dto.formation())
                .airToGround(dto.airToGround())
                .build();
        return rank;
    }
}
