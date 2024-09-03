package com.example.sql_queries.dao;

import com.example.sql_queries.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Long> {
    Customer findByEmail(String email);
}
