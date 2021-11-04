package com.scalefocus.java.simeonyachev.brewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalefocus.java.simeonyachev.brewery.services.BeerService;
import com.scalefocus.java.simeonyachev.brewery.web.model.BeerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    private static final String BEER_URI = "/api/v1/beer/";

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDTO beer;

    @BeforeEach
    public void setUp() {
        beer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .name("Ариана")
                .style("Тъмно")
                .upc(123L)
                .build();
    }

    @Test
    void getByIdSuccessfully() throws Exception {
        when(beerService.getById(any(UUID.class))).thenReturn(beer);

        mockMvc.perform(get(BEER_URI + beer.getId().toString()).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(beer.getId().toString()))
                .andExpect(jsonPath("$.name").value("Ариана"));
    }

    @Test
    void saveBeerSuccessfully() throws Exception {
        String beerJson = objectMapper.writeValueAsString(beer);

        when(beerService.saveBeer(any())).thenReturn(beer);

        mockMvc.perform(post(BEER_URI)
                .contentType(APPLICATION_JSON)
                .content(beerJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerSuccessfully() throws Exception {
        String beerJson = objectMapper.writeValueAsString(beer);

        mockMvc.perform(put(BEER_URI + beer.getId().toString())
                .contentType(APPLICATION_JSON)
                .content(beerJson))
                .andExpect(status().isOk());

        verify(beerService).updateBeer(any(), any());
    }

    @Test
    void deleteByIdSuccessfully() throws Exception {
        mockMvc.perform(delete(BEER_URI + beer.getId().toString()))
                .andExpect(status().isNoContent());

        verify(beerService).deleteById(any());
    }
}