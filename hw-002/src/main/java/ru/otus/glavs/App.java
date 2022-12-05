package ru.otus.glavs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.glavs.service.ExamService;

@ComponentScan(basePackages = "ru.otus.glavs")
public class App {
    public static void main(String[] args) {
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        ExamService exam = context.getBean(ExamService.class);
        exam.examine();


    }
}
