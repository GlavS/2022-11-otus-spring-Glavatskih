package ru.glavs.composerervice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NewstyleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewstyleServiceApplication.class, args);
	}

}
