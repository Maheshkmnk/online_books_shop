package com.example.sql_queries.service.impl;

import com.example.sql_queries.dao.CustomerDao;
import com.example.sql_queries.dto.LoginRequestDto;
import com.example.sql_queries.dto.RegisterCustomerRequestDto;
import com.example.sql_queries.dto.responseDto.ResponseDto;
import com.example.sql_queries.entity.Customer;
import com.example.sql_queries.mappers.CustomerPopulator;
import com.example.sql_queries.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public ResponseDto register(RegisterCustomerRequestDto registerCustomerRequestDto) {
        Customer customerEntity = CustomerPopulator.INSTANCE.toEntity(registerCustomerRequestDto);
        Customer customer = customerDao.save(customerEntity);
        return CustomerPopulator.INSTANCE.toDto(customer);
    }

    @Override
    public ResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }

    @Override
    public List<ResponseDto> getCustomerDetailsByEmail(String email) {
        return null;
    }

    @Override
    public Customer updateCustomerDetailsByEmail(RegisterCustomerRequestDto registerCustomerRequestDto) {
        return null;
    }

    @Override
    public String updatePassword() {
        return null;
    }

    @Override
    public String processCsvFile(MultipartFile file) {

        return null;
    }
}
