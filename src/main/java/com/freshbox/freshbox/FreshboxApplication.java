package com.freshbox.freshbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages={"com.freshbox.freshbox"})

public class FreshboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreshboxApplication.class, args);
	}

}
