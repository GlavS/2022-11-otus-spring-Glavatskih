package ru.otus.glavs;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.glavs.service.ExamService;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        ExamService exam = context.getBean(ExamService.class);
        exam.examine();


    }
}
