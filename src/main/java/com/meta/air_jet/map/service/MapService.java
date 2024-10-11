package com.meta.air_jet.map.service;


import com.meta.air_jet.map.domain.Map;
import com.meta.air_jet.map.domain.dto.MapRequestDTO;
import com.meta.air_jet.map.repository.MapRepository;
import com.meta.air_jet.mission.Mission;
import com.meta.air_jet.mission.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MapService {
    private final MapRepository mapRepository;
    private final MissionRepository missionRepository;

    public void save(MapRequestDTO.mapCreateDTO dto) {
        duplicateMapName(dto.mapName());

        List<Long> missionsIds = new ArrayList<>();
        // saveAllFlush 고려
        List<Mission> missions = missionRepository.saveAll(dto.mission());
        missions.forEach(mission -> missionsIds.add(mission.getId()));

        Map map = Map.builder()
                .mapName(dto.mapName())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .producer(dto.producer())
                .missionIds(missionsIds)
                .build();
        mapRepository.save(map);
    }

    private void duplicateMapName(String mapName) {
        Map findMap = mapRepository.findByMapName(mapName);
        if (findMap != null) {
            throw new IllegalArgumentException("맵 이름이 중복되었습니다. map name: " + mapName);
        }
    }
}
