package com.meta.air_jet;

import com.meta.air_jet.mission.Mission;

import java.util.List;

public class TestRequestDTO {
    public record mapDTO(
            String mapName,
            double latitude,
            double longitude,
            String producer,
            List<Mission> mission
    ) {
    }
}
