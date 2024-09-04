package com.example.sql_queries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterEmployeeRequestDto {
    private String ename;
    private String email;
    private Integer age;
    private String password;
}
