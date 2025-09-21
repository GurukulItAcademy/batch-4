package com.nextalien.accounts.mapper;

import com.nextalien.accounts.dto.CustomerDto;
import com.nextalien.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapTOCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());

        return customer;
    }

}
