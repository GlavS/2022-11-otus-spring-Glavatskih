package ru.otus.glavs.service;

import org.springframework.stereotype.Service;
import ru.otus.glavs.dao.StudentDao;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.helper.ConsoleHelper;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao dao;
    private final ConsoleHelper ch;

    public StudentServiceImpl(StudentDao dao, ConsoleHelper ch) {
        this.dao = dao;
        this.ch = ch;
    }

    @Override
    public Student register() {
        // при обращении к реальной БД здесь будет логика регистрации на экзамен
        String name = ch.readStringWithPrompt("Please enter your first name:");
        String surname = ch.readStringWithPrompt("Please enter your family name:");
        return dao.registerNew(name, surname);
    }
}
