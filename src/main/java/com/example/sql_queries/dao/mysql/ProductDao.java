package com.example.sql_queries.dao.mysql;

import com.example.sql_queries.entity.mysql.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Long> {


}
