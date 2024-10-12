package com.example.sql_queries.dto.request_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDataDto {
    private String name;
    private Integer age;
    private String email;
    private String password;
}
