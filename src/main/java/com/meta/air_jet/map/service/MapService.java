package com.meta.air_jet.map.service;


import com.meta.air_jet.firebase.FireBaseService;
import com.meta.air_jet.map.domain.Map;
import com.meta.air_jet.map.domain.dto.MapRequestDTO;
import com.meta.air_jet.map.repository.MapRepository;
import com.meta.air_jet.mission.Mission;
import com.meta.air_jet.mission.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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

    public void save(MultipartFile mapImage, MapRequestDTO.mapCreateDTO dto) throws IOException {
        duplicateMapName(dto.mapName());

        List<Long> missionsIds = new ArrayList<>();
        List<Mission> missions = missionRepository.saveAll(dto.mission());
        missions.forEach(mission -> missionsIds.add(mission.getId()));
        String fileName = fireBaseService.uploadImage(mapImage);
        Map map = Map.builder()
                .mapName(dto.mapName())
                .mapImage(fileName)
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .producer(dto.producer())
                .missionIds(missionsIds)
                .createAt(LocalDateTime.now())
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

    public Page<Map> findAllMap(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createAt").descending());
        return mapRepository.findAllSorted(pageable);
    }
}
