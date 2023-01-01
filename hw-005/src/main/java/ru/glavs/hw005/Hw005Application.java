package ru.glavs.hw005;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.service.BookDisplayService;
import ru.glavs.hw005.service.DisplayService;

import java.sql.SQLException;


@SpringBootApplication
public class Hw005Application {

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Hw005Application.class, args);
        DisplayService<Book> bookDisplay = ctx.getBean(BookDisplayService.class);
        bookDisplay.displayAll();
    }

}
