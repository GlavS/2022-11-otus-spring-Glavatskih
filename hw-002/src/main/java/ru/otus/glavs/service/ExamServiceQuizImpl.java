package ru.otus.glavs.service;

import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.helper.ConsoleHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamServiceQuizImpl implements ExamService {

    private final QuizService quizService;
    private final StudentService studentService;
    private final ConsoleHelper ch;


    public ExamServiceQuizImpl(QuizServiceImpl quizService, StudentServiceImpl studentService, ConsoleHelper ch) {
        this.quizService = quizService;
        this.studentService = studentService;
        this.ch = ch;
    }

    @Override
    public void examine() {
        Map<Integer, Boolean> answerList = new HashMap<>();

        ch.writeMessage("Starting our exam...");
        Student student = studentService.register();
        List<Quiz> questionList = quizService.getQuestionList();

        ch.writeMessage("==========================================");
        ch.writeMessage("Examination rules: read question and pick one of three answers");
        ch.writeMessage("by typing 1, 2 or 3");
        ch.writeMessage(System.lineSeparator());


        for (Quiz question :
                questionList) {
            quizService.displayQuestion(question);
            ch.writeMessage("Please specify your answer(1, 2 or 3):");
            int answer = ch.readInt();
            while (answer < 1 || answer > 3) {
                ch.writeMessage("Incorrect answer number, please retry (1, 2 or 3):");
                answer = ch.readInt();
            }
            boolean isCorrect = (answer == question.getCorrectAnswer());
            answerList.put(answer, isCorrect);
        }

        ch.writeMessage("Exam results review:");
        int correctAnswerCount = (int) answerList.values().stream().filter(isCorrect -> isCorrect).count();
        boolean isPassed = (correctAnswerCount > 3);
        String examResult = isPassed? "Exam is passed" : "Exam is not passed";
        ch.writeMessage(examResult);
        ch.write(String.format("You correctly answered %d questions of 5", correctAnswerCount));

    }
}
