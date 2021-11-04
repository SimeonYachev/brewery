package com.scalefocus.java.simeonyachev.brewery.web.model.v2;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTOV2 {
    private UUID id;
    private String name;
    private BeerStyle style;
    private Long upc;
}
