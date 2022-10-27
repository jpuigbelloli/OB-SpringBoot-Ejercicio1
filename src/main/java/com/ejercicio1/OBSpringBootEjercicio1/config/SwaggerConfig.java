package com.ejercicio1.OBSpringBootEjercicio1.config;

import com.ejercicio1.OBSpringBootEjercicio1.controller.LaptopController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/*
* Configuración Swagger para la generación de documentación de la API REST
* http://localhost:8081/swagger-ui
*/

@Configuration
public class SwaggerConfig {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    @Bean
    public Docket api(){
        log.warn("creating docket API");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiDetails(){
        log.warn("creating ApiInfo ApiDetails");
        return new ApiInfo("Spring Boot Laptops API REST",
                "Library Api rest docs",
                "1.0",
                "http://www.google.com",
                new Contact("Jose","http://www.google.com", "josepuigbelloli@gmail.com"),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());
    }

}
