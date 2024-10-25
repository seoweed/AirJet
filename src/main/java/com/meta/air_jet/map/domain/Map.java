package com.meta.air_jet.map.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_map")
public class Map {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mapName;
    private String mapImage;
    private double latitude;
    private double longitude;
    private String producer;
    @ElementCollection
    private List<Long> missionIds;
    private LocalDateTime createAt;

    @Builder
    public Map(String mapName, String mapImage, double latitude, double longitude, String producer, List<Long> missionIds, LocalDateTime createAt) {
        this.mapName = mapName;
        this.mapImage = mapImage;
        this.latitude = latitude;
        this.longitude = longitude;
        this.producer = producer;
        this.missionIds = missionIds;
        this.createAt = createAt;
    }
}
