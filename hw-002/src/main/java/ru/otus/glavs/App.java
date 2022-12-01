package ru.otus.glavs;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.glavs.dao.QuizDao;
import ru.otus.glavs.dao.QuizDaoCsvImpl;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.QuizService;
import ru.otus.glavs.service.helper.ConsoleHelper;

public class App {
    public static void main(String[] args) {
       ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
//        QuizService quizService = context.getBean(QuizService.class);
//        quizService.displayAll();

        ConsoleHelper ch = context.getBean(ConsoleHelper.class);
        String input = ch.readString();
        ch.writeMessage(input);
        int intInp = ch.readInt();
        ch.writeMessage(String.valueOf(intInp));

    }
}
