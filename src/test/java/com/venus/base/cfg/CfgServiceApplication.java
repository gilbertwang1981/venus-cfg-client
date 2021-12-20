package com.venus.base.cfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.venus.base")
public class CfgServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CfgServiceApplication.class, args);
	}
}
