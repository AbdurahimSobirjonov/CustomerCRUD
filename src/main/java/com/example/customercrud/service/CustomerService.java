package com.example.customercrud.service;

import com.example.customercrud.dto.ApiResponse;
import com.example.customercrud.dto.CustomerDTO;
import com.example.customercrud.entity.Customer;
import com.example.customercrud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public List<Customer> customerList (){
        List<Customer> all = customerRepository.findAll();
        return all;
    }

    public Customer getCustomerById(Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        Customer customer=byId.get();
        return customer;
    }
    public void deleteCustomerById(Integer id){
        customerRepository.deleteById(id);
    }

    public ApiResponse saveCustomer(CustomerDTO customerDTO){
        boolean existsByPhone = customerRepository.existsByPhone(customerDTO.getPhone());
        if (existsByPhone){
            return new ApiResponse("Bunday customer mavjud",false);
            }
        Customer customer = new Customer();
        customer.setAddress(customerDTO.getAddress());
        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customerRepository.save(customer);
        return new ApiResponse("Customer saqlandi",true);
    }
    public ApiResponse updateCustomer(CustomerDTO customerDTO,Integer id){
        boolean existsById = customerRepository.existsById(id);
        if (existsById){
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
            if (optionalCustomer.isPresent()){
                Customer customer = optionalCustomer.get();
                customer.setName(customerDTO.getName());
                customer.setPhone(customerDTO.getPhone());
                customer.setAddress(customerDTO.getAddress());
                customerRepository.save(customer);
            }
            return new ApiResponse("Customer ozgardi",true);
        }
        return new ApiResponse("Bunday id customer mavjud emas",false);
    }
}
