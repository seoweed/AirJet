package com.meta.air_jet.map.repository;

import com.meta.air_jet.map.domain.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, Long> {
    Map findByMapName(String mapName);

    @Query("select m.mapName from Map m")
    List<String> findAllMapName();

    @Query("SELECT m FROM Map m ORDER BY m.createAt DESC")
    Page<Map> findAllSorted(Pageable pageable);
}
