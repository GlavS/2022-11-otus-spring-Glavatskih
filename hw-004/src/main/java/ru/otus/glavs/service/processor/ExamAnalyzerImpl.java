package ru.otus.glavs.service.processor;

import org.springframework.stereotype.Component;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.properties.Application;
import ru.otus.glavs.service.QuizService;
import ru.otus.glavs.service.helper.ConsoleHelper;

import java.util.Map;

@Component
public class ExamAnalyzerImpl implements ExamAnalyzer {
    private final ConsoleHelper ch;

    private final QuizService quizService;

    private final int minCorrectAnswersCount; // количество правильных ответов, минимально достаточное для прохождения теста
    private final LocalizedMessages storage;

    public ExamAnalyzerImpl(ConsoleHelper ch, QuizService quizService,
                            Application props, LocalizedMessages storage) {
        this.ch = ch;
        this.quizService = quizService;
        this.minCorrectAnswersCount = props.getMinCorrectAnswers();
        this.storage = storage;
    }


    @Override
    public void printExamResults(Student student, Map<Integer, Answer> answerMap) {
        ch.writeMessage("==========================================" + System.lineSeparator());
        ch.writeMessage(storage.getText("examanalyzer.printresults.caption"));
        int correctAnswerCount = (int) answerMap.values().stream().filter(Answer::isCorrect).count();
        boolean isPassed = (correctAnswerCount >= minCorrectAnswersCount);
        String examResult = isPassed
                ? storage.getText("examanalyzer.printresults.exampassed")
                : storage.getText("examanalyzer.printresults.examnotpassed");
        ch.writeMessage(examResult);
        ch.write(String.format(storage.getText("examanalyzer.printresults.results"),
                student.getName(),
                student.getSurname(),
                correctAnswerCount));
        ch.writeMessage(System.lineSeparator());
        if (correctAnswerCount < 5) {
            printMistakes(answerMap);
        }
    }

    private void printMistakes(Map<Integer, Answer> answerMap) {
        ch.writeMessage(storage.getText("examanalyzer.printmistakess.caption"));
        for (Map.Entry<Integer, Answer> answerEntry :
                answerMap.entrySet()) {
            if (!answerEntry.getValue().isCorrect()) {
                int questionNumber = answerEntry.getKey();
                Quiz question = quizService.getQuestionById(questionNumber);
                String questionText = question.getQuestion();
                String givenAnswer = quizService.getAnswerByNumber(question, answerEntry.getValue().getVariant());
                String correctAnswer = quizService.getAnswerByNumber(question, question.getCorrectAnswer());
                String resultMistake = String.format(storage.getText("examanalyzer.printmistakess.result"), questionNumber,
                        questionText, givenAnswer, correctAnswer);
                ch.write(resultMistake);
            }
        }
    }
}
