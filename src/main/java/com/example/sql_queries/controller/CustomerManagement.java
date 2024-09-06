package com.example.sql_queries.controller;


import com.example.sql_queries.dto.RegisterCustomerRequestDto;
import com.example.sql_queries.dto.responseDto.ResponseDto;
import com.example.sql_queries.service.impl.CustomerServiceImpl;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerManagement {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterCustomerRequestDto registerDto) {
        ResponseDto register = customerServiceImpl.register(registerDto);
        if (register != null) {
            System.out.println("not null: "+register);
            return new ResponseEntity<>(register, HttpStatus.OK);
        } else {
            System.out.println("null: "+register);
            return new ResponseEntity<>("nothing", HttpStatus.OK);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<List<RegisterCustomerRequestDto>> uploadFile(@RequestParam("file") MultipartFile file){
        return new ResponseEntity<>(customerServiceImpl.processCsvFile(file), HttpStatus.OK);
    }
}
