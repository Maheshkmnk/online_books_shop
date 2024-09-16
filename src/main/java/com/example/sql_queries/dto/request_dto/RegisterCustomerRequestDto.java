package com.example.sql_queries.dto.request_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterCustomerRequestDto {
    private String cname;
    private String email;
    private Integer age;
    private String password;
    private List<AddressDto> address;
}
