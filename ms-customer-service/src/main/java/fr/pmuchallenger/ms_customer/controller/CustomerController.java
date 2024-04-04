package fr.pmuchallenger.ms_customer.controller;

import fr.pmuchallenger.ms_customer.model.dto.request.AddCustomerRequest;
import fr.pmuchallenger.ms_customer.model.dto.response.CustomerDto;
import fr.pmuchallenger.ms_customer.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Api(tags = "Customer Management", description = "Endpoints for managing customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ApiOperation(value = "Create a new customer")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody AddCustomerRequest addCustomerRequest) {
        log.debug("Request to create customer: {}", addCustomerRequest);
        return new ResponseEntity<>(customerService.addCustomer(addCustomerRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a customer by ID")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        log.debug("Request to get customer: {}", id);
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a customer")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @Valid @RequestBody AddCustomerRequest addCustomerRequest) {
        log.debug("Request received to update customer with id : {}", id);
        return new ResponseEntity<>(customerService.updateCustomer(id, addCustomerRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a customer")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.debug("Request to delete customer with id : {}", id);
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
