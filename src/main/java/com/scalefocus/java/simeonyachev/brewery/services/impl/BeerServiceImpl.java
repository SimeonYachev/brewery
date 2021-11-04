package com.scalefocus.java.simeonyachev.brewery.services.impl;

import com.scalefocus.java.simeonyachev.brewery.services.BeerService;
import com.scalefocus.java.simeonyachev.brewery.web.model.BeerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDTO getById(UUID beerId) {
        return BeerDTO.builder()
                .id(UUID.randomUUID())
                .name("Ариана")
                .style("Тъмно")
                .build();
    }

    @Override
    public BeerDTO saveBeer(BeerDTO beerDTO) {
        return BeerDTO.builder()
                .id(UUID.randomUUID())
                .name(beerDTO.getName())
                .style(beerDTO.getStyle())
                .upc(beerDTO.getUpc())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDTO beerDTO) {
        //todo impl - would add a real impl to update beer
        log.debug("Updating a beer with id {}...", beerId);
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("Deleting a beer with id {}...", beerId);
    }
}
