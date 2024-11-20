package com.meta.air_jet.manvoice;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VocAllResponseDTO {
    private Long id;
    private String voice;
    private String description;
}
