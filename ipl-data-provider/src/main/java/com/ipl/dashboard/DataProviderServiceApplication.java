package com.ipl.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ipl.dashboard")
public class DataProviderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataProviderServiceApplication.class, args);
    }
}
