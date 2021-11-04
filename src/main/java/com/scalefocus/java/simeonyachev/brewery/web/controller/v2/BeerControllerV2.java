package com.scalefocus.java.simeonyachev.brewery.web.controller.v2;

import com.scalefocus.java.simeonyachev.brewery.services.v2.BeerServiceV2;
import com.scalefocus.java.simeonyachev.brewery.web.model.v2.BeerDTOV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v2/beer")
public class BeerControllerV2 {

    private static final String LOCATION_HEADER = "Location";
    private static final String BEER_URL = "http://localhost:8080/api/v1/beer/";

    private final BeerServiceV2 beerServiceV2;

    public BeerControllerV2(BeerServiceV2 beerServiceV2) {
        this.beerServiceV2 = beerServiceV2;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTOV2> getBeer(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerServiceV2.getById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTOV2> handlePost(@RequestBody BeerDTOV2 beerDTO) {
        BeerDTOV2 savedDTO = beerServiceV2.saveBeer(beerDTO);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add(LOCATION_HEADER, BEER_URL + savedDTO.getId().toString());

        return new ResponseEntity<>(savedDTO, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDTOV2> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTOV2 beerDTO) {
        beerServiceV2.updateBeer(beerId, beerDTO);
        return new ResponseEntity<>(beerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerServiceV2.deleteById(beerId);
    }
}
