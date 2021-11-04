package com.scalefocus.java.simeonyachev.brewery.services.impl;

import com.scalefocus.java.simeonyachev.brewery.services.CustomerService;
import com.scalefocus.java.simeonyachev.brewery.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDTO getById(UUID customerId) {
        return CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Калин4о")
                .build();
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name(customerDTO.getName())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDTO customerDTO) {
        //todo impl
        log.debug("Updating a customer with id {}...", customerId);
    }

    @Override
    public void deleteById(UUID customerId) {
        log.debug("Deleting customer with id {}...", customerId);
    }
}
