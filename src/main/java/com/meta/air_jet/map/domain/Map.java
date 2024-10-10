package com.meta.air_jet.map.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_map")
public class Map {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
