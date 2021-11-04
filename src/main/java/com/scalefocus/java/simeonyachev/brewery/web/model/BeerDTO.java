package com.scalefocus.java.simeonyachev.brewery.web.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {
    private UUID id;
    private String name;
    private String style;
    private Long upc;
}
