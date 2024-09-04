package com.example.sql_queries.exceptions.individual_exceptions;

public class DuplicateEntryException extends RuntimeException{
    public DuplicateEntryException(String message){
        super(message);
    }

    public DuplicateEntryException(String message, Throwable cause){
        super(message, cause);
    }
}
