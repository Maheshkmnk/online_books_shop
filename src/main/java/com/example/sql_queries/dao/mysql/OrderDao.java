package com.example.sql_queries.dao.mysql;

import com.example.sql_queries.entity.mysql.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Orders,Long> {

}
