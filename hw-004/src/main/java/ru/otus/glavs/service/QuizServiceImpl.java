package ru.otus.glavs.service;

import org.springframework.stereotype.Service;
import ru.otus.glavs.dao.QuizDao;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.helper.ConsoleHelper;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizDao dao;
    private final ConsoleHelper ch;
    private final LocalizedMessages storage;

    public QuizServiceImpl(QuizDao dao, ConsoleHelper ch,
                           LocalizedMessages storage) {
        this.dao = dao;
        this.ch = ch;
        this.storage = storage;
    }

    @Override
    public void displayAllQuestions() {
        List<Quiz> quizList = dao.getAll();
        for (Quiz quiz :
                quizList) {
            displayQuestion(quiz);
        }
    }

    @Override
    public void displayQuestion(Quiz question) {
        //TODO storage.get* methods
        ch.write(String.format(storage.getText("quizservice.display.question"), question.getId(), question.getQuestion()));

        String variant = storage.getText("quizservice.display.variant");
        ch.write(String.format(variant, 1, question.getAnswer1()));
        ch.write(String.format(variant, 2, question.getAnswer2()));
        ch.write(String.format(variant, 3, question.getAnswer3()));
        ch.write(System.lineSeparator());
    }

    @Override
    public List<Quiz> getQuestionList() {
        return dao.getAll();
    }

    @Override
    public Quiz getQuestionById(int id) {
        return dao.getById(id);
    }

    @Override
    public String getAnswerByNumber(Quiz quiz, int number) {
        if (number == 1) {
            return quiz.getAnswer1();
        } else if (number == 2) {
            return quiz.getAnswer2();
        } else if (number == 3) {
            return quiz.getAnswer3();
        } else {
            return storage.getText("quizservice.answerbynumber.error");
        }
    }
}
