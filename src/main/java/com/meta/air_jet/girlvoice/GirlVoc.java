package com.meta.air_jet.girlvoice;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_girl_voice")
public class GirlVoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String voice;
    private Integer idx;

    @Builder
    public GirlVoc(String description, String voice, Integer idx) {
        this.description = description;
        this.voice = voice;
        this.idx = idx;
    }
}
