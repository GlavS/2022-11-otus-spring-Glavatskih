package ru.otus.glavs.service.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.QuizService;
import ru.otus.glavs.service.helper.ConsoleHelper;

import java.util.Map;

@Component
public class ExamAnalyzerImpl implements ExamAnalyzer {
    private final ConsoleHelper ch;

    private final QuizService quizService;

    @Value("${application.minCorrectAnswers}")
    private int minCorrectAnswersCount; // количество правильных ответов, минимально достаточное для прохождения теста

    public ExamAnalyzerImpl(ConsoleHelper ch, QuizService quizService) {
        this.ch = ch;

        this.quizService = quizService;
    }


    @Override
    public void printExamResults(Student student, Map<Integer, Answer> answerMap) {
        ch.writeMessage("==========================================" + System.lineSeparator());
        ch.writeMessage("Exam results review:");
        int correctAnswerCount = (int) answerMap.values().stream().filter(Answer::isCorrect).count();
        boolean isPassed = (correctAnswerCount >= minCorrectAnswersCount);
        String examResult = isPassed ? "Exam is passed" : "Exam is not passed";
        ch.writeMessage(examResult);
        ch.write(String.format("Student %s %s correctly answered %d questions of 5", student.getName(), student.getSurname(), correctAnswerCount));
        ch.writeMessage(System.lineSeparator());
        if (correctAnswerCount < 5) {
            printMistakes(answerMap);
        }
    }

    private void printMistakes(Map<Integer, Answer> answerMap) {
        ch.writeMessage("Incorrect answers:");
        for (Map.Entry<Integer, Answer> answerEntry :
                answerMap.entrySet()) {
            if (!answerEntry.getValue().isCorrect()) {
                int questionNumber = answerEntry.getKey();
                Quiz question = quizService.getQuestionById(questionNumber);
                String questionText = question.getQuestion();
                String givenAnswer = quizService.getAnswerByNumber(question, answerEntry.getValue().getVariant());
                String correctAnswer = quizService.getAnswerByNumber(question, question.getCorrectAnswer());
                ch.write(String.format("Answer on question %d: \"%s\" was %s, but correct answer is %s%n",
                        questionNumber, questionText, givenAnswer, correctAnswer));
            }
        }
    }
}
