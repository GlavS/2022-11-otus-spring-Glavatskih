package ru.otus.glavs.service.processor;

import ru.otus.glavs.domain.Student;

import java.util.Map;

public interface ExamAnalyzer {
    void printExamResults(Student student, Map<Integer, Answer> answerMap);
}
