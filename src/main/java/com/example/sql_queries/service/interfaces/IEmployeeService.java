package com.example.sql_queries.service.interfaces;

import com.example.sql_queries.dto.request_dto.RegisterEmployeeRequestDto;
import com.example.sql_queries.dto.responseDto.EmployeeResponseDto;

import java.util.List;

public interface IEmployeeService {
    EmployeeResponseDto registerEmployee(RegisterEmployeeRequestDto dto);

    public List<EmployeeResponseDto> registerAll();

}
