package ru.glavs.hw005;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.h2.tools.Console;

import java.sql.SQLException;


@SpringBootApplication
public class Hw005Application{

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Hw005Application.class, args);
		Console.main();
	}

}
