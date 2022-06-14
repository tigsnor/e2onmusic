package com.example.warproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
public class WarprojectApplication extends SpringServletContainerInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WarprojectApplication.class, args);
    }

}
