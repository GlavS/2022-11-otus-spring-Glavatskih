package ru.otus.glavs.service;

import org.springframework.stereotype.Service;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.ioservice.IOService;
import ru.otus.glavs.service.processor.Answer;
import ru.otus.glavs.service.processor.ExamAnalyzer;
import ru.otus.glavs.service.processor.ExamProcessor;

import java.util.Map;


@Service
public class ExamServiceQuizImpl implements ExamService {
    private final ExamProcessor examProcessor;
    private final ExamAnalyzer examAnalyzer;
    private final StudentService studentService;
    private final IOService ch;
    private final LocalizedMessages storage;


    public ExamServiceQuizImpl(ExamProcessor examProcessor,
                               ExamAnalyzer examAnalyzer,
                               StudentService studentService,
                               IOService ch,
                               LocalizedMessages storage) {
        this.examProcessor = examProcessor;
        this.examAnalyzer = examAnalyzer;
        this.studentService = studentService;
        this.ch = ch;
        this.storage = storage;
    }

    @Override
    public void examine() {
        Student student = studentService.register();
        ch.writeln(storage.getTextMessage("examservicequiz.greeting") + System.lineSeparator());
        Map<Integer, Answer> answerMap = examProcessor.collectAnswers();
        examAnalyzer.printExamResults(student, answerMap);
    }
}
