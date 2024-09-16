package com.example.sql_queries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SqlQueriesApplication {
	public static void main(String[] args) {
		SpringApplication.run(SqlQueriesApplication.class, args);
	}
}
