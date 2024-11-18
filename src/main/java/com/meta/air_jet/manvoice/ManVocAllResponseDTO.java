package com.meta.air_jet.manvoice;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ManVocAllResponseDTO {
    private Long id;
    private String voice;
    private String description;
}
