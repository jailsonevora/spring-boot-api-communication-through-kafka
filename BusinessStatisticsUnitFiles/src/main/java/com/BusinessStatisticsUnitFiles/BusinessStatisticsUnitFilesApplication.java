package com.BusinessStatisticsUnitFiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.BusinessStatisticsUnitFiles"})
@EntityScan(basePackages = {"com.BusinessStatisticsUnitFiles"})
@ComponentScan(basePackages = {"com.BusinessStatisticsUnitFiles"})
@Configuration
public class BusinessStatisticsUnitFilesApplication {
    public static void main(String[] args) {

        SpringApplication.run(BusinessStatisticsUnitFilesApplication.class, args);
    }
}
