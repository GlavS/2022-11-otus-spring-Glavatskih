package ru.otus.glavs.service;

import ru.otus.glavs.service.helper.ConsoleHelper;

public class ExamServiceQuizImpl implements ExamService {

    private final QuizServiceImpl quizService;
    private final StudentServiceImpl studentService;
    private final ConsoleHelper ch;

    public ExamServiceQuizImpl(QuizServiceImpl quizService, StudentServiceImpl studentService, ConsoleHelper ch) {
        this.quizService = quizService;
        this.studentService = studentService;
        this.ch = ch;
    }

    @Override
    public void examine() {


    }

    @Override
    public void printResults() {

    }
}
