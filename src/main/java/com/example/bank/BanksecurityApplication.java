package com.example.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BanksecurityApplication extends SpringBootServletInitializer {

	public static void main(String[] args)  {
		SpringApplication.run(BanksecurityApplication.class, args);
	}
	
	 @Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    	return builder.sources(BanksecurityApplication.class);
	    }


}

