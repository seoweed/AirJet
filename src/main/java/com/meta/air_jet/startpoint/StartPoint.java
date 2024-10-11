package com.meta.air_jet.startpoint;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_start_point")
public class StartPoint {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int x;
    private int y;

    @Builder
    public StartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
