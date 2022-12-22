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
    private final IOService ioService;

    private final QuizService quizService;

    private final int minCorrectAnswersCount; // количество правильных ответов, минимально достаточное для прохождения теста
    private final LocalizedMessages messagesProvider;

    public ExamAnalyzerImpl(IOService ioService, QuizService quizService,
                            Application props, LocalizedMessages provider) {
        this.ioService = ioService;
        this.quizService = quizService;
        this.minCorrectAnswersCount = props.getMinCorrectAnswers();
        this.messagesProvider = provider;
    }


    @Override
    public void printExamResults(Student student, Map<Integer, Answer> answerMap) {
        ioService.writeln("==========================================" + System.lineSeparator());
        ioService.writeln(messagesProvider.getTextMessage("examanalyzer.printresults.caption"));
        int correctAnswerCount = (int) answerMap.values().stream().filter(Answer::isCorrect).count();
        boolean isPassed = (correctAnswerCount >= minCorrectAnswersCount);
        String examResult = isPassed
                ? messagesProvider.getTextMessage("examanalyzer.printresults.exampassed")
                : messagesProvider.getTextMessage("examanalyzer.printresults.examnotpassed");
        ioService.writeln(examResult);
        ioService.write(String.format(messagesProvider.getTextMessage("examanalyzer.printresults.results"),
                student.getName(),
                student.getSurname(),
                correctAnswerCount));
        ioService.writeln(System.lineSeparator());
        if (correctAnswerCount < 5) {
            printMistakes(answerMap);
        }
    }

    private void printMistakes(Map<Integer, Answer> answerMap) {
        ioService.writeln(messagesProvider.getTextMessage("examanalyzer.printmistakess.caption"));
        for (Map.Entry<Integer, Answer> answerEntry :
                answerMap.entrySet()) {
            if (!answerEntry.getValue().isCorrect()) {
                int questionNumber = answerEntry.getKey();
                Quiz question = quizService.getQuestionById(questionNumber);
                String questionText = question.getQuestion();
                String givenAnswer = quizService.getAnswerByNumber(question, answerEntry.getValue().getVariant());
                String correctAnswer = quizService.getAnswerByNumber(question, question.getCorrectAnswer());
                String resultMistake = String.format(messagesProvider.getTextMessage("examanalyzer.printmistakess.result"), questionNumber,
                        questionText, givenAnswer, correctAnswer);
                ioService.write(resultMistake);
            }
        }
    }
}
