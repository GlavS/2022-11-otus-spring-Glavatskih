package ru.otus.glavs;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.glavs.dao.QuizDao;
import ru.otus.glavs.dao.QuizDaoCsvImpl;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.QuizService;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        QuizService quizService = context.getBean(QuizService.class);
        quizService.displayAll();
    }
}
