package ru.otus.glavs.service;

import org.springframework.stereotype.Service;
import ru.otus.glavs.dao.StudentDao;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.helper.ConsoleHelper;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao dao;
    private final ConsoleHelper ch;
    private final LocalizedMessages storage;

    public StudentServiceImpl(StudentDao dao, ConsoleHelper ch, LocalizedMessages storage) {
        this.dao = dao;
        this.ch = ch;
        this.storage = storage;
    }

    @Override
    public Student register() {
        // при обращении к реальной БД здесь будет логика регистрации на экзамен
        String name = ch.readStringWithPrompt(storage.getText("studentservice.register.firstname.prompt"));
        String surname = ch.readStringWithPrompt(storage.getText("studentservice.register.lastname.prompt"));
        return dao.registerNew(name, surname);
    }
}
