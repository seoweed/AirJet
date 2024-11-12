package com.meta.air_jet.result;

public class ResultResponseDTO {
    public record saveDTO(
            int rank,
            String comment
    ) {
    }
}
