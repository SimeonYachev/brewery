package com.scalefocus.java.simeonyachev.brewery.web.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Size;
import javax.validation.constraints.Null;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @Null
    private UUID id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
}
