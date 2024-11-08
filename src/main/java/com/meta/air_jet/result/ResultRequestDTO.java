package com.meta.air_jet.result;

import java.time.LocalDateTime;

public class ResultRequestDTO {
    public record saveDTO(
            Long playTime,
            int engineStart,
            int takeOff,
            int formation,
            int airToGround
    ){}
}
