package com.example.sql_queries.dao;

import com.example.sql_queries.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Orders,Long> {

}
