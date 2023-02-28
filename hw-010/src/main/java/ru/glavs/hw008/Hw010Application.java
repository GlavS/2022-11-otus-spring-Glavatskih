package ru.glavs.hw008;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class Hw010Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw010Application.class);
        System.out.println("Main page: http://localhost:8080");
    }
}
