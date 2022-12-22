package ru.otus.glavs.service;

import org.springframework.stereotype.Service;
import ru.otus.glavs.dao.QuizDao;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.ioservice.IOService;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizDao dao;
    private final IOService ioService;
    private final LocalizedMessages messagesProvider;

    public QuizServiceImpl(QuizDao dao, IOService ioService,
                           LocalizedMessages provider) {
        this.dao = dao;
        this.ioService = ioService;
        this.messagesProvider = provider;
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
        ioService.write(String.format(messagesProvider.getTextMessage("quizservice.display.question"),
                question.getId(),
                question.getQuestion()));

        String variant = messagesProvider.getTextMessage("quizservice.display.variant");
        ioService.write(String.format(variant, 1, question.getAnswer1()));
        ioService.write(String.format(variant, 2, question.getAnswer2()));
        ioService.write(String.format(variant, 3, question.getAnswer3()));
        ioService.write(System.lineSeparator());
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
            return messagesProvider.getTextMessage("quizservice.answerbynumber.error");
        }
    }
}
