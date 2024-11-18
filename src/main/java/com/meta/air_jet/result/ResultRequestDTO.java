package com.meta.air_jet.result;

import java.time.LocalDateTime;

public class ResultRequestDTO {
    public record saveDTO(
            float engineStart,
            float takeOff,
            float formation,
            float airToGround
    ){}

}
