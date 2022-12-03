package ru.otus.glavs;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.glavs.service.QuizServiceImpl;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        QuizServiceImpl quizService = context.getBean(QuizServiceImpl.class);
        quizService.displayAllQuestions();

//        ConsoleHelper ch = context.getBean(ConsoleHelper.class);
//        String input = ch.readString();
//        ch.writeMessage(input);
//        int intInp = ch.readInt();
//        ch.writeMessage(String.valueOf(intInp));

    }
}
