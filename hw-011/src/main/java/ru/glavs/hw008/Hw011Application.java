package ru.glavs.hw008;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

//@EnableMongock
@EnableWebFlux
@EnableReactiveMongoRepositories
@SpringBootApplication
public class Hw011Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw011Application.class);
        System.out.println("Main page: http://localhost:8080");
    }
}
