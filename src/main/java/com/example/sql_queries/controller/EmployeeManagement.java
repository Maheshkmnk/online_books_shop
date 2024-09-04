package com.example.sql_queries.controller;


import com.example.sql_queries.dto.RegisterEmployeeRequestDto;
import com.example.sql_queries.dto.responseDto.EmployeeResponseDto;
import com.example.sql_queries.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeManagement {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<EmployeeResponseDto> registerEmployee(
            @RequestBody RegisterEmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto employeeResponseDto = employeeServiceImpl.registerEmployee(employeeRequestDto);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
    }

}

