package ru.glavs.hw005;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.h2.tools.Console;
import org.springframework.context.ConfigurableApplicationContext;
import ru.glavs.hw005.dao.BookDao;

import java.sql.SQLException;


@SpringBootApplication
public class Hw005Application{

	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext ctx = SpringApplication.run(Hw005Application.class, args);
		BookDao bookDao = ctx.getBean(BookDao.class);
		System.out.println(bookDao.getAll());
		//Console.main();
	}

}
