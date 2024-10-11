package com.meta.air_jet.map.domain.dto;

import com.meta.air_jet.mission.Mission;

import java.util.List;

public class MapRequestDTO {
    public record mapCreateDTO(
        String mapName,
//        String image,
        double latitude,
        double longitude,
        String producer,
        List<Mission> mission
    )
    {}

    public record getMapDataDTO(
            String mapName
    ){}
}
