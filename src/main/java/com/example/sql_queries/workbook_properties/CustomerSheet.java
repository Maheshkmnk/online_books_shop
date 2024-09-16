package com.example.sql_queries.workbook_properties;

import org.springframework.beans.factory.annotation.Value;

public class CustomerSheet {
    @Value("${}")
    private String cname;
    private String email;
    private String age;
    private String password;
    private String address;
}
