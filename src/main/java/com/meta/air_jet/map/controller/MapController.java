package com.meta.air_jet.map.controller;

import com.meta.air_jet._core.utils.ApiUtils;
import com.meta.air_jet.map.domain.dto.MapRequestDTO;
import com.meta.air_jet.map.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MapController {
    private final MapService mapService;

    @PostMapping("/map/create")
    public ResponseEntity<?> save(@RequestBody MapRequestDTO.mapCreateDTO dto) {
        System.out.println("dto = " + dto.toString());
        try {
            mapService.save(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiUtils.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ApiUtils.success("맵 저장을 완료했습니다."), HttpStatus.OK);

    }

}
