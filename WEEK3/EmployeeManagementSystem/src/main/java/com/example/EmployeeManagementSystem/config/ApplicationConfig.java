package com.example.employeemanagementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.employeemanagementsystem.repository")
@EnableJpaAuditing
public class ApplicationConfig {
    
}
