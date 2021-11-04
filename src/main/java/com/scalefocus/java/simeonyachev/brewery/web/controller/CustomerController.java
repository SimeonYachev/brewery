package com.scalefocus.java.simeonyachev.brewery.web.controller;

import com.scalefocus.java.simeonyachev.brewery.services.CustomerService;
import com.scalefocus.java.simeonyachev.brewery.web.model.CustomerDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private static final String LOCATION_HEADER = "Location";
    private static final String CUSTOMER_URL = "http://localhost:8080/api/v1/customer/";

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<>(customerService.getById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedDTO = customerService.saveCustomer(customerDTO);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add(LOCATION_HEADER, CUSTOMER_URL + savedDTO.getId().toString());

        return new ResponseEntity<>(customerDTO, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("customerId") UUID customerId,
                                                      @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerId, customerDTO);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("customerId") UUID customerId) {
        customerService.deleteById(customerId);
    }
}
