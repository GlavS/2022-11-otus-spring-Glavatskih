package ru.otus.glavs.service.processor;

import ru.otus.glavs.domain.Student;

import java.util.Map;

public interface ExamProcessor {
    Map<Integer, Answer> collectAnswers();

}
