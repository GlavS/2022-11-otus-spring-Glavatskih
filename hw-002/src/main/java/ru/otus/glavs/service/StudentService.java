package ru.otus.glavs.service;

import ru.otus.glavs.dao.StudentDao;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.helper.ConsoleHelper;

public class StudentService {
    private final StudentDao dao;
    private final ConsoleHelper ch;

    public StudentService(StudentDao dao, ConsoleHelper ch) {
        this.dao = dao;
        this.ch = ch;
    }
    public Student register(){
        // при обращении к реальной БД здесь будет логика регистрации на экзамен
        String name = ch.readStringWithPrompt("Please enter your first name:");
        String surname = ch.readStringWithPrompt("Please enter your family name:");
        return dao.registerNew(name, surname);
    }
}
