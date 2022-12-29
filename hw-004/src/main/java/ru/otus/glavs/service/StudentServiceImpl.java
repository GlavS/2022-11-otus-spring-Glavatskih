package ru.otus.glavs.service;

import org.springframework.stereotype.Service;
import ru.otus.glavs.dao.StudentDao;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.ioservice.LocalizedIOService;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao dao;
    private final LocalizedIOService ioService;

    public StudentServiceImpl(StudentDao dao, LocalizedIOService ioService) {
        this.dao = dao;
        this.ioService = ioService;
    }

    @Override
    public Student register() {
        // при обращении к реальной БД здесь будет логика регистрации на экзамен
        String name = ioService.readStringWithPrompt("studentservice.register.firstname.prompt");
        String surname = ioService.readStringWithPrompt("studentservice.register.lastname.prompt");
        return dao.registerNew(name, surname);
    }
}
