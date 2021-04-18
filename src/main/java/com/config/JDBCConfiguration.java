package com.config;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JDBCConfiguration {

	private static final String DB_URL = "";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";
	private static Connection connection = null;

	@Bean
	public static Connection getConnection() {
		//TODO
		
		return connection;
	}

}
