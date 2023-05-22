package ru.glavs.composerervice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import ru.glavs.composerervice.repository.ComposerRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages = "ru.glavs.composerervice.feign")
public class ComposersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComposersServiceApplication.class, args);
	}

}
