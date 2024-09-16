package com.example.sql_queries.service.interfaces;

import com.example.sql_queries.dto.request_dto.RegisterEmployeeRequestDto;
import com.example.sql_queries.dto.responseDto.EmployeeResponseDto;

public interface IEmployeeService {
    EmployeeResponseDto registerEmployee(RegisterEmployeeRequestDto dto);
}
