package com.meta.air_jet.map.repository;

import com.meta.air_jet.map.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long> {
    Map findByMapName(String mapName);

    @Query("select m.mapName from Map m")
    List<String> findAllMapName();
}
