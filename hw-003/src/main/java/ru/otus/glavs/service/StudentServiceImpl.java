package ru.otus.glavs.service;

import org.springframework.stereotype.Service;
import ru.otus.glavs.dao.StudentDao;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.l10n.LocalizedStudentServiceMessageStorage;
import ru.otus.glavs.service.helper.ConsoleHelper;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao dao;
    private final ConsoleHelper ch;
    private final LocalizedStudentServiceMessageStorage storage;

    public StudentServiceImpl(StudentDao dao, ConsoleHelper ch, LocalizedStudentServiceMessageStorage storage) {
        this.dao = dao;
        this.ch = ch;
        this.storage = storage;
    }

    @Override
    public Student register() {
        // при обращении к реальной БД здесь будет логика регистрации на экзамен
        String name = ch.readStringWithPrompt(storage.getRegisterFirstNamePrompt());
        String surname = ch.readStringWithPrompt(storage.getRegisterLastNamePrompt());
        return dao.registerNew(name, surname);
    }
}
