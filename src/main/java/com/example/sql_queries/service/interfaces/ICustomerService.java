package com.example.sql_queries.service.interfaces;

import com.example.sql_queries.dto.RegisterCustomerRequestDto;
import com.example.sql_queries.dto.LoginRequestDto;
import com.example.sql_queries.dto.responseDto.ResponseDto;
import com.example.sql_queries.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICustomerService {
    ResponseDto register(RegisterCustomerRequestDto registerCustomerRequestDto);
    ResponseDto login(LoginRequestDto loginRequestDto);
    List<ResponseDto> getCustomerDetailsByEmail(String email);
    Customer updateCustomerDetailsByEmail(RegisterCustomerRequestDto registerCustomerRequestDto);

    String updatePassword();

    String processCsvFile(MultipartFile file);
}
