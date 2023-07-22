package com.example.customercrud.controller;

import com.example.customercrud.dto.ApiResponse;
import com.example.customercrud.dto.CustomerDTO;
import com.example.customercrud.entity.Customer;
import com.example.customercrud.service.CustomerService;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ApiResponse addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}


