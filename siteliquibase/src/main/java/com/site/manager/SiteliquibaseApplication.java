package com.site.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SiteliquibaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteliquibaseApplication.class, args);
	}

}
