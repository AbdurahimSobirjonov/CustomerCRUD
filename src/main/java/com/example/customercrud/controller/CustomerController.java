package com.example.customercrud.controller;

import com.example.customercrud.dto.ApiResponse;
import com.example.customercrud.dto.CustomerDTO;
import com.example.customercrud.entity.Customer;
import com.example.customercrud.service.CustomerService;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping
    public List<Customer> customers (){
        List<Customer> customers = customerService.customerList();
        return customers;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable Integer id){
        Customer customerById = customerService.getCustomerById(id);
        return customerById;
    }
    @PostMapping
    public ApiResponse addCustomer(@RequestBody CustomerDTO customerDTO){
        ApiResponse apiResponse = customerService.saveCustomer(customerDTO);
        return apiResponse;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id){
        customerService.deleteCustomerById(id);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ApiResponse updateCustomer(@PathVariable Integer id,@RequestBody CustomerDTO customerDTO){
        ApiResponse apiResponse = customerService.updateCustomer(customerDTO,id);
        return apiResponse;
    }

}
