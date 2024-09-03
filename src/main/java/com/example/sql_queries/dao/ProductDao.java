package com.example.sql_queries.dao;

import com.example.sql_queries.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Long> {


}
