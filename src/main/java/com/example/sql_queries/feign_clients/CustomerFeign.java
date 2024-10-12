package com.example.sql_queries.feign_clients;

import com.example.sql_queries.dto.request_dto.CustomerDataDto;
import com.example.sql_queries.dto.responseDto.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
public class CustomerFeign {
    private final WebClient webClient;

    public CustomerFeign(WebClient.Builder webclientBuilder){
        this.webClient=webclientBuilder.baseUrl("http://localhost:8080").build();
    }
    public List<CustomerDataDto> getAllUser(){
      return  webClient.get()
                .uri("/customer/get-all-customers")
                .retrieve()
                .bodyToFlux(CustomerDataDto.class)
                .collectList()
                .block();
    }

//    @GetMapping("/customer/get-customer-by-name/{name}")
//    Flux<CustomerResponseDto> getCustomersByName(@PathVariable String name);
//
//    @DeleteMapping("/customer/delete-customer-by-id/{id}")
//    Mono<Void> deleteCustomerById(@PathVariable Long id);
//
//    @GetMapping("/customer/get-customer-by-id")
//    Mono<CustomerResponseDto> getCustomerId(@RequestParam Long id);
//
//    @PostMapping("/customer/register")
//    Mono<CustomerResponseDto> registerCustomer(@RequestBody RegisterCustomer customer);
}
