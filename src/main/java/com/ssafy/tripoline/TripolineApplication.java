package com.ssafy.tripoline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})
public class TripolineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripolineApplication.class, args);
	}
	
}
