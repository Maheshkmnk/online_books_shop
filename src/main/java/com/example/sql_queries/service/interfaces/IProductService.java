package com.example.sql_queries.service.interfaces;

import com.example.sql_queries.entity.mysql.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();


}
