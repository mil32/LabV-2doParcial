package com.utn.parcialLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ParcialLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcialLabApplication.class, args);
	}

}
