package com.scalefocus.java.simeonyachev.brewery.web.controller;

import com.scalefocus.java.simeonyachev.brewery.services.BeerService;
import com.scalefocus.java.simeonyachev.brewery.web.model.BeerDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private static final String LOCATION_HEADER = "Location";
    private static final String BEER_URL = "http://localhost:8080/api/v1/beer/";

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTO> saveBeer(@Valid @RequestBody BeerDTO beerDTO) {
        BeerDTO savedDTO = beerService.saveBeer(beerDTO);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add(LOCATION_HEADER, BEER_URL + savedDTO.getId().toString());

        return new ResponseEntity<>(savedDTO, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDTO> updateBeer(@PathVariable("beerId") UUID beerId,
                                              @Valid @RequestBody BeerDTO beerDTO) {
        beerService.updateBeer(beerId, beerDTO);
        return new ResponseEntity<>(beerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
    }
}
