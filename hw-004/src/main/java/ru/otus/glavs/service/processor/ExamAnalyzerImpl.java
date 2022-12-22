package ru.otus.glavs.service.processor;

import org.springframework.stereotype.Component;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.properties.Application;
import ru.otus.glavs.service.QuizService;
import ru.otus.glavs.service.ioservice.IOService;

import java.util.Map;

@Component
public class ExamAnalyzerImpl implements ExamAnalyzer {
    private final IOService ch;

    private final QuizService quizService;

    private final int minCorrectAnswersCount; // количество правильных ответов, минимально достаточное для прохождения теста
    private final LocalizedMessages storage;

    public ExamAnalyzerImpl(IOService ch, QuizService quizService,
                            Application props, LocalizedMessages storage) {
        this.ch = ch;
        this.quizService = quizService;
        this.minCorrectAnswersCount = props.getMinCorrectAnswers();
        this.storage = storage;
    }


    @Override
    public void printExamResults(Student student, Map<Integer, Answer> answerMap) {
        ch.writeln("==========================================" + System.lineSeparator());
        ch.writeln(storage.getTextMessage("examanalyzer.printresults.caption"));
        int correctAnswerCount = (int) answerMap.values().stream().filter(Answer::isCorrect).count();
        boolean isPassed = (correctAnswerCount >= minCorrectAnswersCount);
        String examResult = isPassed
                ? storage.getTextMessage("examanalyzer.printresults.exampassed")
                : storage.getTextMessage("examanalyzer.printresults.examnotpassed");
        ch.writeln(examResult);
        ch.write(String.format(storage.getTextMessage("examanalyzer.printresults.results"),
                student.getName(),
                student.getSurname(),
                correctAnswerCount));
        ch.writeln(System.lineSeparator());
        if (correctAnswerCount < 5) {
            printMistakes(answerMap);
        }
    }

    private void printMistakes(Map<Integer, Answer> answerMap) {
        ch.writeln(storage.getTextMessage("examanalyzer.printmistakess.caption"));
        for (Map.Entry<Integer, Answer> answerEntry :
                answerMap.entrySet()) {
            if (!answerEntry.getValue().isCorrect()) {
                int questionNumber = answerEntry.getKey();
                Quiz question = quizService.getQuestionById(questionNumber);
                String questionText = question.getQuestion();
                String givenAnswer = quizService.getAnswerByNumber(question, answerEntry.getValue().getVariant());
                String correctAnswer = quizService.getAnswerByNumber(question, question.getCorrectAnswer());
                String resultMistake = String.format(storage.getTextMessage("examanalyzer.printmistakess.result"), questionNumber,
                        questionText, givenAnswer, correctAnswer);
                ch.write(resultMistake);
            }
        }
    }
}
