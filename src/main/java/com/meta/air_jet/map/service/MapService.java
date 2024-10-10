package com.meta.air_jet.map.service;

import com.meta.air_jet.map.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MapService {
    private final MapRepository mapRepository;

}
