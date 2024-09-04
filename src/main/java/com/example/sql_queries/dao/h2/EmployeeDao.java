package com.example.sql_queries.dao.h2;

import com.example.sql_queries.entity.h2.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
