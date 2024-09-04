package com.example.sql_queries.mappers;

import com.example.sql_queries.entity.h2.Employee;
import com.example.sql_queries.dto.RegisterEmployeeRequestDto;
import com.example.sql_queries.dto.responseDto.EmployeeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeePopulator {
    EmployeePopulator INSTANCE= Mappers.getMapper(EmployeePopulator.class);

//    @Mapping(target = "eid", ignore = true)
    Employee toEntity(RegisterEmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto toDto(Employee employee);
}
