package com.eosa;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EosaApplication {

	public static void main(String[] args) {
		System.setProperty("CurrentTime",String.valueOf(LocalDate.now() + "-") + String.valueOf(System.currentTimeMillis()));
		SpringApplication.run(EosaApplication.class, args);
	}

}
