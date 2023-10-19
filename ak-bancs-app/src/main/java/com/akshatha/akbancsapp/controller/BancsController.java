package com.akshatha.akbancsapp.controller;

import com.akshatha.akbancsapp.dto.CustomerDto;
import com.akshatha.akbancsapp.model.Customer;
import com.akshatha.akbancsapp.repository.CustomerRepository;
import com.akshatha.akbancsapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/akbancs")
public class BancsController {

    @Autowired
    CustomerService customerService;
    CustomerRepository customerRepository;

    @GetMapping(value = "/customers/customerNames")
    public ResponseEntity<List<CustomerDto>> getCustomerNames_v1() {
        //List<CustomerDto> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") Long customerId) {

        //customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping("/customers")   //Json body input is given from postman, so @request body is used.
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto) {  //Forward direction. DTO class--> Entity class taking input as customerDTO because we should not direclty perform db operation on customer entity class.

        Customer newCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/all")
    public ResponseEntity<HttpStatus> deleteAllCustomers() {
        customerService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/customers")
    public ResponseEntity<HttpStatus> deleteByCustomerId(@RequestParam(defaultValue = "12345") Long customerId) { // id is passed in url in the form of key value pair. ie, ?key=value&key=value....so on.
        customerService.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/customers/{customerIdentity}")
    //another way of writing to delete the customer by Id ,if id is coming from request url path.
    public ResponseEntity<HttpStatus> deleteByCustomerIdentity(@PathVariable("customerId") Long customerId) { //if we want to change the name of the path variable then only explicitely write @PathVariable("CUSTOM NAME"). Otherwise method will take the name in the path variable itself. Here it is customerIdentity.
        customerService.deleteById(customerId); //calling service class where all business logic is written.
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/customers/update/{customerId}")
    public ResponseEntity<Customer> updateCustomerData(@RequestBody CustomerDto customerDto ,@PathVariable Long customerId) {

        Customer newData = customerService.updateCustomer(customerDto,customerId);
        return new ResponseEntity<>(newData, HttpStatus.ACCEPTED);
    }

}
