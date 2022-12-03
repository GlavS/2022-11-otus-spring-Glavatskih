package ru.otus.glavs.service;

import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.helper.ConsoleHelper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        Student student = studentService.register();
        printGreeting();
        Map<Integer, Answer> answerMap = makeAnswerMap();
        printExamResults(student, answerMap);
    }

    private void printGreeting() {
        ch.writeMessage("Starting our exam...");
        ch.writeMessage("==========================================");
        ch.writeMessage("Examination rules: read question and pick one of three answers");
        ch.writeMessage("by typing 1, 2 or 3");
        ch.writeMessage(System.lineSeparator());
    }

    private Map<Integer, Answer> makeAnswerMap() {
        Map<Integer, Answer> answerMap = new TreeMap<>();
        List<Quiz> questionList = quizService.getQuestionList();
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
            answerMap.put(question.getId(), new Answer(answer, isCorrect));
        }
        return answerMap;
    }

    private void printExamResults(Student student, Map<Integer, Answer> answerMap) {
        ch.writeMessage("Exam results review:");
        int correctAnswerCount = (int) answerMap.values().stream().filter(ans -> ans.isCorrect).count();
        boolean isPassed = (correctAnswerCount > 3);
        String examResult = isPassed ? "Exam is passed" : "Exam is not passed";
        ch.writeMessage(examResult);
        ch.write(String.format("%s %s correctly answered %d questions of 5", student.getName(), student.getSurname(), correctAnswerCount));
        ch.writeMessage(System.lineSeparator());
    }

    private static final class Answer {
        private final int variant;
        private final boolean isCorrect;

        private Answer(int variant, boolean isCorrect) {
            this.variant = variant;
            this.isCorrect = isCorrect;
        }
    }
}
