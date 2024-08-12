package com.ideas2it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.EmployeeServiceImpl;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

	@Bean
	public EmployeeService employeeService() {
		return new EmployeeServiceImpl();
	}
}
