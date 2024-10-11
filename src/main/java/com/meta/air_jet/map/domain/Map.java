package com.meta.air_jet.map.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_map")
public class Map {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mapName;
    //    private String mapImage;
    private double latitude;
    private double longitude;
    private String producer;
    private Long startPointId;
    @ElementCollection
    private List<Long> missionIds;

    @Builder

    public Map(String mapName, double latitude, double longitude, String producer, Long startPointId, List<Long> missionIds) {
        this.mapName = mapName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.producer = producer;
        this.startPointId = startPointId;
        this.missionIds = missionIds;
    }
}
