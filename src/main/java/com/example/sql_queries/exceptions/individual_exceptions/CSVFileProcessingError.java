package com.example.sql_queries.exceptions.individual_exceptions;

public class CSVFileProcessingError extends RuntimeException {
    public CSVFileProcessingError(String message) {
        super(message);
    }
}
