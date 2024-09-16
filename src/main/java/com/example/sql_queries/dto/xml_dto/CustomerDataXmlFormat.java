package com.example.sql_queries.dto.xml_dto;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(namespace = "cust")
public class CustomerDataXmlFormat {
    private Long id;
    private String name;
    private String mail;
    private Integer age;
}
