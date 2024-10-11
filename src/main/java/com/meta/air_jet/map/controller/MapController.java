package com.meta.air_jet.map.controller;

import com.meta.air_jet._core.utils.ApiUtils;
import com.meta.air_jet.map.domain.Map;
import com.meta.air_jet.map.domain.dto.MapRequestDTO;
import com.meta.air_jet.map.service.MapService;
import com.meta.air_jet.mission.Mission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MapController {
    private final MapService mapService;

    @PostMapping("/map/create")
    public ResponseEntity<?> save(@RequestBody MapRequestDTO.mapCreateDTO dto) {
        try {
            mapService.save(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiUtils.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ApiUtils.success("맵 저장을 완료했습니다."), HttpStatus.OK);
    }

    @PostMapping("/map/data")
    public HashMap<String, Object> mapData(@RequestBody MapRequestDTO.getMapDataDTO dto) {
        HashMap<String, Object> outputMapData = new HashMap<>();
        HashMap<String, Object> outputStartPoint = new HashMap<>();

        Map map = mapService.getMapInfo(dto.mapName());
        ArrayList<Mission> mapMissions = mapService.getMapMissions(map.getMissionIds());

        Mission startPointMission = mapMissions.stream().filter(mission -> mission.getPinNo() == 1).findAny().get();
        // start point 값 추가
        outputStartPoint.put("x", startPointMission.getX());
        outputStartPoint.put("y", startPointMission.getY());

        // map data 추가
        outputMapData.put("mapName", map.getMapName());
        // 이미지 값 수정
        outputMapData.put("mapImage", null);
        outputMapData.put("latitude", map.getLatitude());
        outputMapData.put("longitude", map.getLongitude());
        outputMapData.put("producer", map.getProducer());
        outputMapData.put("mission", mapMissions);
        outputMapData.put("startPoint", outputStartPoint);

        return outputMapData;
    }
}
