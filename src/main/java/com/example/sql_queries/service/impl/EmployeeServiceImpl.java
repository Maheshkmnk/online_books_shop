package com.example.sql_queries.service.impl;

import com.example.sql_queries.dao.h2.EmployeeDao;
import com.example.sql_queries.dto.request_dto.RegisterEmployeeRequestDto;
import com.example.sql_queries.dto.responseDto.EmployeeResponseDto;
import com.example.sql_queries.entity.h2.Employee;
import com.example.sql_queries.mappers.EmployeePopulator;
import com.example.sql_queries.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public EmployeeResponseDto registerEmployee(RegisterEmployeeRequestDto dto) {
        Employee entity = EmployeePopulator.INSTANCE.toEntity(dto);
        Employee emp = employeeDao.save(entity);
        return EmployeePopulator.INSTANCE.toDto(emp);
    }
}
