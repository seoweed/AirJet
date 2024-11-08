package com.meta.air_jet.result;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_result")
public class Result {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long playTime;
    private int engineStart;
    private int takeOff;
    private int formation;
    private int airToGround;
    private int rank;
    public LocalDateTime createAt;

    @Builder
    public Result(Long memberId, Long playTime, int engineStart, int takeOff, int formation, int airToGround, int rank, LocalDateTime createAt) {
        this.memberId = memberId;
        this.playTime = playTime;
        this.engineStart = engineStart;
        this.takeOff = takeOff;
        this.formation = formation;
        this.airToGround = airToGround;
        this.rank = rank;
        this.createAt = createAt;
    }
}
