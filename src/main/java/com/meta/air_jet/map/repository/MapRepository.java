package com.meta.air_jet.map.repository;

import com.meta.air_jet.map.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Long> {
}
