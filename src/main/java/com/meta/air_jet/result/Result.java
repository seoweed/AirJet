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
    private Long userId;
    private Long playTime;
    private float engineStart;
    private float takeOff;
    private float formation;
    private float airToGround;
    private int rank;
    private String comment;
    public LocalDateTime createAt;

    @Builder

    public Result(Long userId, Long playTime, float engineStart, float takeOff, float formation, float airToGround, int rank, String comment, LocalDateTime createAt) {
        this.userId = userId;
        this.playTime = playTime;
        this.engineStart = engineStart;
        this.takeOff = takeOff;
        this.formation = formation;
        this.airToGround = airToGround;
        this.rank = rank;
        this.comment = comment;
        this.createAt = createAt;
    }
}
