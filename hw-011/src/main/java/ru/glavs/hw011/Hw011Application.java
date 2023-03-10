package ru.glavs.hw011;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableReactiveMongoRepositories
@SpringBootApplication
public class Hw011Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw011Application.class);
        System.out.println("Main page: http://localhost:8080");


    }
}
