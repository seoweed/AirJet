package com.meta.air_jet.mission;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tbl_mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int pinNo;
    private int x;
    private int y;
    private int commandNo;

    @Builder

    public Mission(int pinNo, int x, int y, int commandNo) {
        this.pinNo = pinNo;
        this.x = x;
        this.y = y;
        this.commandNo = commandNo;
    }
}