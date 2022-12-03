package ru.otus.glavs.service;

import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.domain.Student;

public class QuizExamService implements Exam{
    private Student student;
    private Quiz quiz;

    public QuizExamService(Student student, Quiz quiz) {
        this.student = student;
        this.quiz = quiz;
    }



    @Override
    public void examine() {

    }

    @Override
    public void printResults() {

    }
}
