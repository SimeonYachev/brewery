package com.scalefocus.java.simeonyachev.brewery.services.v2;

import com.scalefocus.java.simeonyachev.brewery.web.model.v2.BeerDTOV2;

import java.util.UUID;

public interface BeerServiceV2 {
    BeerDTOV2 getById(UUID beerId);

    BeerDTOV2 saveBeer(BeerDTOV2 beerDTO);

    void updateBeer(UUID beerId, BeerDTOV2 beerDTO);

    void deleteById(UUID beerId);
}
