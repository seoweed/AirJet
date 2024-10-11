package com.meta.air_jet.map.controller;

import com.meta.air_jet.map.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MapController {
    private final MapService mapService;

}
