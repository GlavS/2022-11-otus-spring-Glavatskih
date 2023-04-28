package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class Hw015Application {
    public static void main(String[] args){
        SpringApplication.run(Hw015Application.class, args);
    }
}
