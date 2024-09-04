package com.example.sql_queries.service.interfaces;

import com.example.sql_queries.dto.RegisterEmployeeRequestDto;
import com.example.sql_queries.dto.responseDto.EmployeeResponseDto;
import com.example.sql_queries.mappers.EmployeePopulator;

public interface IEmployeeService {
    EmployeeResponseDto registerEmployee(RegisterEmployeeRequestDto dto);
}
