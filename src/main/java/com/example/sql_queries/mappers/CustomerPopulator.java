package com.example.sql_queries.mappers;

import com.example.sql_queries.dto.request_dto.RegisterCustomerRequestDto;
import com.example.sql_queries.dto.responseDto.ResponseDto;
import com.example.sql_queries.entity.mysql.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerPopulator {
    CustomerPopulator INSTANCE= Mappers.getMapper(CustomerPopulator.class);

//    @Mapping(target="cid", ignore=true)
    Customer toEntity(RegisterCustomerRequestDto registerCustomerRequestDto);
    ResponseDto toDto(Customer customer);
}
