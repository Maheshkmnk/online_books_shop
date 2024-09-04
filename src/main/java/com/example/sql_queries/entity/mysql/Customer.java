package com.example.sql_queries.entity.mysql;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cid")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    @Column(nullable = false)
    private String cname;
    @Column(nullable = false, unique = true)
    private String email;
    private Integer age;
    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Address> address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Product> product;
}
