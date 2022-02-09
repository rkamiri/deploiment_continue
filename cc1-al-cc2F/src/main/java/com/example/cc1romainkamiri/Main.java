package com.example.cc1romainkamiri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
	}

}
