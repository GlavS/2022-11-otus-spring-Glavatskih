package ru.otus.glavs.service.processor;

import java.util.Map;

public interface ExamProcessor {
    Map<Integer, Answer> collectAnswers();
}
