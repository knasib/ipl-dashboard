package com.ipl.dashboard;

import com.ipl.dashboard.config.AstraSecureBundleProperties;
import com.ipl.dashboard.input.InputFileFieldName;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@Slf4j
@SpringBootApplication
public class IplDataLoaderService implements CommandLineRunner {

    @Value("${input.file}")
    private String fileName;

    public static void main(String[] args) {
        SpringApplication.run(IplDataLoaderService.class, args);
    }

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(AstraSecureBundleProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectionBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }

    @Override
    public void run(String... args) throws Exception {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(Objects.requireNonNull(this.getClass().getResource("/" + fileName)).getPath()))
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()) {
            List<String[]> r = reader.readAll();
            r.stream().limit(10).forEach(x -> {
                System.out.println(x[InputFileFieldName.id.ordinal()]);
                //match();
                //team();
            });
        }
    }
}
