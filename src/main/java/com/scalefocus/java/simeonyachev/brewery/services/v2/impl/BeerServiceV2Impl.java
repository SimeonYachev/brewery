package com.scalefocus.java.simeonyachev.brewery.services.v2.impl;

import com.scalefocus.java.simeonyachev.brewery.services.v2.BeerServiceV2;
import com.scalefocus.java.simeonyachev.brewery.web.model.v2.BeerDTOV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDTOV2 getById(UUID beerId) {
        return null;
    }

    @Override
    public BeerDTOV2 saveBeer(BeerDTOV2 beerDTO) {
        return null;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDTOV2 beerDTO) {

    }

    @Override
    public void deleteById(UUID beerId) {

    }
}
