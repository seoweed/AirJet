package com.meta.air_jet.map.service;


import com.meta.air_jet.firebase.FireBaseService;
import com.meta.air_jet.map.domain.Map;
import com.meta.air_jet.map.domain.dto.MapRequestDTO;
import com.meta.air_jet.map.repository.MapRepository;
import com.meta.air_jet.mission.Mission;
import com.meta.air_jet.mission.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MapService {
    private final MapRepository mapRepository;
    private final MissionRepository missionRepository;
    private final FireBaseService fireBaseService;

    public Map getMapInfo(String mapName) {
        return mapRepository.findByMapName(mapName);
    }

    public ArrayList<Mission> getMapMissions(List<Long> missionIds) {
        ArrayList<Mission> mapMissions = new ArrayList<>();
        missionIds.forEach(missionId -> {
            Mission mission = missionRepository.findById(missionId).get();
            mapMissions.add(mission);
        });
        return mapMissions;
    }

    public void save(MapRequestDTO.mapCreateDTO dto) throws IOException {
        duplicateMapName(dto.mapName());

        List<Long> missionsIds = new ArrayList<>();
        List<Mission> missions = missionRepository.saveAll(dto.mission());
        missions.forEach(mission -> missionsIds.add(mission.getId()));
        // Multipart 이미지 파일로 들어와야 하는데 지금 만들 예정인 dto는 base 64 로 인코딩 된 이미지 파일이 들어올 예정임
//        String fileName = fireBaseService.uploadImage(dto.mapImage());
        Map map = Map.builder()
                .mapName(dto.mapName())
//                .mapImage(fileName)
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

    public List<String> getMapNameList() {
        return mapRepository.findAllMapName();
    }
}
