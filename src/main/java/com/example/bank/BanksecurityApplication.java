package com.example.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BanksecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanksecurityApplication.class, args);
	}
	
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    	return builder.sources(BanksecurityApplication.class);
	    }


}

