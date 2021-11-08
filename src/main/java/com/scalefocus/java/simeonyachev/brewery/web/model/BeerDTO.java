package com.scalefocus.java.simeonyachev.brewery.web.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {

    @Null
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String style;
    @Positive
    private Long upc;
}
