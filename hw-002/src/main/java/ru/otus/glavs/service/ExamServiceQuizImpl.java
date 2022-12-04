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
    private void printMistakes(Map<Integer, Answer> answerMap){
        ch.writeMessage("Incorrect answers:");
        for (Map.Entry<Integer, Answer> entry :
                answerMap.entrySet()) {
            if(!entry.getValue().isCorrect){
                int questionNumber = entry.getKey();
                Quiz question = quizService.getQuestionById(questionNumber);
                String questionText = question.getQuestion();
                String givenAnswer = getAnswerByNumber(question, entry.getValue().variant);
                String correctAnswer = getAnswerByNumber(question, question.getCorrectAnswer());
                ch.write(String.format("Answer on question %d: \"%s\" was %s, but correct answer is %s%n",
                        questionNumber, questionText, givenAnswer, correctAnswer));
            }
        }
    }
    private String getAnswerByNumber(Quiz question, int number){
        if(number == 1){
            return question.getAnswer1();
        } else if (number == 2) {
            return question.getAnswer2();
        } else if (number == 3) {
            return question.getAnswer3();
        } else {
            return "No such answer";
        }
    }

    private void printExamResults(Student student, Map<Integer, Answer> answerMap) {
        ch.writeMessage("Exam results review:");
        int correctAnswerCount = (int) answerMap.values().stream().filter(ans -> ans.isCorrect).count();
        boolean isPassed = (correctAnswerCount > 3);
        String examResult = isPassed ? "Exam is passed" : "Exam is not passed";
        ch.writeMessage(examResult);
        ch.write(String.format("Student %s %s correctly answered %d questions of 5", student.getName(), student.getSurname(), correctAnswerCount));
        ch.writeMessage(System.lineSeparator());
        if(correctAnswerCount < 5) {
            printMistakes(answerMap);
        }
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
