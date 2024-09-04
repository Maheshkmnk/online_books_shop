package com.example.sql_queries.dto.responseDto;

import com.example.sql_queries.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private Long eid;
    private String ename;
    private String email;
    private Integer age;
}
