package com.scalefocus.java.simeonyachev.brewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalefocus.java.simeonyachev.brewery.services.CustomerService;
import com.scalefocus.java.simeonyachev.brewery.web.model.CustomerDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    private static final String CUSTOMER_URI = "/api/v1/customer/";

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDTO customer;

    @BeforeEach
    public void setUp() {
        customer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Калин4о")
                .build();
    }

    @Test
    void getByIdSuccessfully() throws Exception {
        when(customerService.getById(any(UUID.class))).thenReturn(customer);

        mockMvc.perform(get(CUSTOMER_URI + customer.getId().toString()).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customer.getId().toString()))
                .andExpect(jsonPath("$.name").value("Калин4о"));
    }

    @Test
    void saveCustomerSuccessfully() throws Exception {
        String customerJson = objectMapper.writeValueAsString(customer);

        when(customerService.saveCustomer(any())).thenReturn(customer);

        mockMvc.perform(post(CUSTOMER_URI)
                .contentType(APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCustomerSuccessfully() throws Exception {
        String customerJson = objectMapper.writeValueAsString(customer);

        mockMvc.perform(put(CUSTOMER_URI + customer.getId().toString())
                .contentType(APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isOk());

        verify(customerService).updateCustomer(any(), any());
    }

    @Test
    void deleteByIdSuccessfully() throws Exception {
        mockMvc.perform(delete(CUSTOMER_URI + customer.getId().toString()))
                .andExpect(status().isNoContent());

        verify(customerService).deleteById(any());
    }

}