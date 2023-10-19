package com.akshatha.akbancsapp.service;

import com.akshatha.akbancsapp.dto.CustomerDto;
import com.akshatha.akbancsapp.model.Customer;
import com.akshatha.akbancsapp.repository.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll(); //fetching customers from database
        List<CustomerDto> customerList = new ArrayList<>(); //created empty list where we will add customer details So it will be mapped to dto.
        for (Customer customer : customers) { //fetching each customer from customerList.
            CustomerDto customerDto = new CustomerDto(customer.getCustomerId(), customer.getCustomerName().toUpperCase()); //mapping customer enitity to customer dto.
            customerList.add(customerDto); //adding each individual customerDto  object details to customerList.
        }
        return customerList;
    }

    public CustomerDto getCustomerById(Long customerId) {

        Optional<Customer> customerData = customerRepository.findById(customerId);
        CustomerDto customerDto = null;
        if (customerData.isPresent()) {
            Customer customer = customerData.get();
            customerDto = new CustomerDto(customer.getCustomerId(), customer.getCustomerName());
        }
        return customerDto;
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public void deleteById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer createCustomer(CustomerDto customerDto) {
        Customer newCustomer = customerRepository.save(new Customer(customerDto.getCustomerId(), customerDto.getCustomerName()));
        return newCustomer;
    }

    public Customer updateCustomer(CustomerDto customerDto, Long customerId) {

        Optional<Customer> fetchedCustomer = customerRepository.findById(customerId);
        if (fetchedCustomer.isPresent()) {
            Customer updatedCustomer = fetchedCustomer.get(); //putting fetched customer in new variable.
            updatedCustomer.setCustomerName(customerDto.getCustomerName()); //setting the customer name in DTO.
            Customer persistedCustomer = customerRepository.save(updatedCustomer); //persisted =saved in db repository.
            return new Customer(persistedCustomer.getCustomerId(), persistedCustomer.getCustomerName()); //returning new saved dto object and display to developer in postman.
        } else {
            System.out.println("Customer not found");
            return new Customer();
        }
    }
}