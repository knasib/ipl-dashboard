package com.ipl.dashboard;

import com.ipl.dashboard.config.AstraSecureBundleProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.nio.file.Path;

@SpringBootApplication
@ComponentScan(basePackages = "com.ipl.dashboard")
public class DataProviderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataProviderServiceApplication.class, args);
    }
}
