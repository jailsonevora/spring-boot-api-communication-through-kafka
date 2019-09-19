package com.BusinessEntityManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.BusinessEntityManagementSystem"})
@EntityScan(basePackages = {"com.BusinessEntityManagementSystem"})
@ComponentScan(basePackages = {"com.BusinessEntityManagementSystem"})
@Configuration
public class BusinessEntityManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(BusinessEntityManagementApplication.class, args);
    }
}
