package com.example.sql_queries.dao.mysql;

import com.example.sql_queries.entity.mysql.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Long> {
    Customer findByEmail(String email);
    boolean existsByEmail(String email);
}
