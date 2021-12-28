package com.ipl.dashboard.config;

import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class CommonConfiguration {

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(AstraSecureBundleProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectionBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }
}
