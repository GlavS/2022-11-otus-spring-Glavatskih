package ru.otus.glavs.service;

import org.springframework.stereotype.Service;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.helper.ConsoleHelper;
import ru.otus.glavs.service.processor.Answer;
import ru.otus.glavs.service.processor.ExamAnalyzer;
import ru.otus.glavs.service.processor.ExamProcessor;

import java.util.Map;


@Service
public class ExamServiceQuizImpl implements ExamService {
    private final ExamProcessor examProcessor;
    private final ExamAnalyzer examAnalyzer;
    private final StudentService studentService;
    private final ConsoleHelper ch;

    private static final String GREETING_MESSAGE =
            "Starting our exam...\n" +
                    "==========================================\n" +
                    "Examination rules: read question and pick one of three answers\n" +
                    "by typing 1, 2 or 3\n" +
                    System.lineSeparator();


    public ExamServiceQuizImpl(ExamProcessor examProcessor, ExamAnalyzer examAnalyzer, StudentService studentService, ConsoleHelper ch) {
        this.examProcessor = examProcessor;
        this.examAnalyzer = examAnalyzer;
        this.studentService = studentService;
        this.ch = ch;
    }

    @Override
    public void examine() {
        Student student = studentService.register();
        ch.writeMessage(GREETING_MESSAGE);
        Map<Integer, Answer> answerMap = examProcessor.collectAnswers();
        examAnalyzer.printExamResults(student, answerMap);
    }
}
