package com.example.sql_queries.entity.mysql;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "oid")
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    private String orderDateAndTime;

    @ManyToMany
    private List<Product> products;

    @ManyToOne()
    private Customer seller;

    @ManyToOne
    private Customer buyer;
}
