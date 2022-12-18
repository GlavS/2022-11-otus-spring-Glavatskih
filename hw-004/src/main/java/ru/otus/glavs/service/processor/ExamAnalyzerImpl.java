package ru.otus.glavs.service.processor;

import org.springframework.stereotype.Component;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.l10n.LocalizedExamAnalyzerMessagesStorage;
import ru.otus.glavs.properties.Application;
import ru.otus.glavs.service.QuizService;
import ru.otus.glavs.service.helper.ConsoleHelper;

import java.util.Map;

@Component
public class ExamAnalyzerImpl implements ExamAnalyzer {
    private final ConsoleHelper ch;

    private final QuizService quizService;

    private final int minCorrectAnswersCount; // количество правильных ответов, минимально достаточное для прохождения теста
    private final LocalizedExamAnalyzerMessagesStorage storage;

    public ExamAnalyzerImpl(ConsoleHelper ch, QuizService quizService,
                            Application props, LocalizedExamAnalyzerMessagesStorage storage) {
        this.ch = ch;
        this.quizService = quizService;
        this.minCorrectAnswersCount = props.getMinCorrectAnswers();
        this.storage = storage;
    }


    @Override
    public void printExamResults(Student student, Map<Integer, Answer> answerMap) {
        ch.writeMessage("==========================================" + System.lineSeparator());
        ch.writeMessage(storage.getPrintResultsCaption());
        int correctAnswerCount = (int) answerMap.values().stream().filter(Answer::isCorrect).count();
        boolean isPassed = (correctAnswerCount >= minCorrectAnswersCount);
        String examResult = isPassed ? storage.getPrintResultsExamPassed() : storage.getPrintResultsExamNotPassed();
        ch.writeMessage(examResult);
        ch.write(String.format(storage.getPrintResultsResults(), student.getName(), student.getSurname(), correctAnswerCount));
        ch.writeMessage(System.lineSeparator());
        if (correctAnswerCount < 5) {
            printMistakes(answerMap);
        }
    }

    private void printMistakes(Map<Integer, Answer> answerMap) {
        ch.writeMessage(storage.getPrintMistakesCaption());
        for (Map.Entry<Integer, Answer> answerEntry :
                answerMap.entrySet()) {
            if (!answerEntry.getValue().isCorrect()) {
                int questionNumber = answerEntry.getKey();
                Quiz question = quizService.getQuestionById(questionNumber);
                String questionText = question.getQuestion();
                String givenAnswer = quizService.getAnswerByNumber(question, answerEntry.getValue().getVariant());
                String correctAnswer = quizService.getAnswerByNumber(question, question.getCorrectAnswer());
                String resultMistake = String.format(storage.getPrintMistakesResult(), questionNumber,
                        questionText, givenAnswer, correctAnswer);
                ch.write(resultMistake);
            }
        }
    }
}
