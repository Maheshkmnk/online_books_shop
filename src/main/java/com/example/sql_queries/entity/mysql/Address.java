package com.example.sql_queries.entity.mysql;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "aid")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;
    private String city;
    private String state;
    private String nationality;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
