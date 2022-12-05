package ru.otus.glavs.service;

import org.springframework.stereotype.Service;
import ru.otus.glavs.dao.QuizDao;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.helper.ConsoleHelperImpl;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizDao dao;
    private final ConsoleHelperImpl ch;

    public QuizServiceImpl(QuizDao dao, ConsoleHelperImpl ch) {
        this.dao = dao;
        this.ch = ch;
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
        ch.write(String.format("Question %d: %s%n", question.getId(), question.getQuestion()));
        ch.write(String.format("   * Variant 1: %s%n", question.getAnswer1()));
        ch.write(String.format("   * Variant 2: %s%n", question.getAnswer2()));
        ch.write(String.format("   * Variant 3: %s%n", question.getAnswer3()));
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

    public String getAnswerByNumber(Quiz quiz, int number) {
        if (number == 1) {
            return quiz.getAnswer1();
        } else if (number == 2) {
            return quiz.getAnswer2();
        } else if (number == 3) {
            return quiz.getAnswer3();
        } else {
            return "No such answer";
        }
    }
}
