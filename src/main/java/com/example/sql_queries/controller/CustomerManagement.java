package com.example.sql_queries.controller;


import com.example.sql_queries.dto.request_dto.RegisterCustomerRequestDto;
import com.example.sql_queries.dto.responseDto.ResponseDto;
import com.example.sql_queries.dto.xml_dto.CustomerDataListXmlFormat;
import com.example.sql_queries.dto.xml_dto.CustomerDataXmlFormat;
import com.example.sql_queries.service.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerManagement {

    @Autowired
    ICustomerService customerServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody RegisterCustomerRequestDto registerDto) {
        ResponseDto register = customerServiceImpl.register(registerDto);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<List<RegisterCustomerRequestDto>> uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(customerServiceImpl.processCsvFile(file), HttpStatus.OK);
    }

    @GetMapping(value = "/xml", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomerDataListXmlFormat> getXmlFormatData() {
        List<CustomerDataXmlFormat> list = Arrays.asList(
                new CustomerDataXmlFormat(1L, "mahesh", "mahesh@gmail.com", 22),
                new CustomerDataXmlFormat(2L, "mahesh", "mahesh@gmail.com", 22),
                new CustomerDataXmlFormat(3L, "mahesh", "mahesh@gmail.com", 22),
                new CustomerDataXmlFormat(4L, "mahesh", "mahesh@gmail.com", 22),
                new CustomerDataXmlFormat(5L, "mahesh", "mahesh@gmail.com", 22)
        );
        CustomerDataListXmlFormat customerDataListXmlFormat = new CustomerDataListXmlFormat(list);
        return new ResponseEntity<>(customerDataListXmlFormat, HttpStatus.OK);
    }
}
