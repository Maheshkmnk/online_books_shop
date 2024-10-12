package com.example.sql_queries.service.impl;

import com.example.sql_queries.dao.h2.EmployeeDao;
import com.example.sql_queries.dto.request_dto.CustomerDataDto;
import com.example.sql_queries.dto.request_dto.RegisterEmployeeRequestDto;
import com.example.sql_queries.dto.responseDto.EmployeeResponseDto;
import com.example.sql_queries.entity.h2.Employee;
import com.example.sql_queries.feign_clients.CustomerFeign;
import com.example.sql_queries.mappers.EmployeePopulator;
import com.example.sql_queries.service.interfaces.IEmployeeService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private CustomerFeign customerFeign;

    @Override
    public EmployeeResponseDto registerEmployee(RegisterEmployeeRequestDto dto) {
        Employee entity = EmployeePopulator.INSTANCE.toEntity(dto);
        Employee emp = employeeDao.save(entity);
        return EmployeePopulator.INSTANCE.toDto(emp);
    }

    @Transactional
    @Override
    public List<EmployeeResponseDto> registerAll() {
        List<CustomerDataDto> employeeList = customerFeign.getAllUser();
        if (employeeList == null) throw new RuntimeException("no data found");

        List<Employee> empList = employeeList.stream()
                .map(data -> {
                    Employee employee = new Employee();
                    BeanUtils.copyProperties(data, employee);
                    employee.setEname(data.getName());
                    employee.setPassword(data.getPassword());
                    return employee;
                }).toList();
        List<Employee> employees = employeeDao.saveAll(empList);
        return employees.stream().map(EmployeePopulator.INSTANCE::toDto).toList();
    }

}
