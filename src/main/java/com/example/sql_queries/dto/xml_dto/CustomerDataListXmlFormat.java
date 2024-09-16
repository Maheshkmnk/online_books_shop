package com.example.sql_queries.dto.xml_dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "customers")
public class CustomerDataListXmlFormat {

    @JacksonXmlElementWrapper(localName = "customer")
    private List<CustomerDataXmlFormat> customerDataXmlFormat;
}
