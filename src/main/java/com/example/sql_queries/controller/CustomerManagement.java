package com.example.sql_queries.controller;


import com.example.sql_queries.dto.RegisterCustomerRequestDto;
import com.example.sql_queries.dto.responseDto.ResponseDto;
import com.example.sql_queries.entity.Customer;
import com.example.sql_queries.service.impl.CustomerServiceImpl;
import com.example.sql_queries.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerManagement {

    @Autowired
    CustomerServiceImpl customerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterCustomerRequestDto registerDto) {
        ResponseDto register = customerService.register(registerDto);
        if (register != null) {
            System.out.println("not null: "+register);
            return new ResponseEntity<>(register, HttpStatus.OK);
        } else {
            System.out.println("null: "+register);
            return new ResponseEntity<>("nothing", HttpStatus.OK);
        }
    }


}
