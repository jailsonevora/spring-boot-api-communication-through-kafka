package com.BusinessEntityManagementSystem;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.BusinessStatisticsUnitFiles"))
                .paths(PathSelectors.ant("/api/businessStatisticsUnitFiles/v1/**"))
                .build()
                .apiInfo(metaData())
				/*.produces(de)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)*/;
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "Business Statistics Unit Files REST API",
                "Business Statistics Unit Files",
                "1.0",
                "TERMS OF SERVICE URL",
                new Contact("Jailson Eduardo Evora", "https://www.linkedin.com/in/jeevora/", ""),

                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
        );
    }
}