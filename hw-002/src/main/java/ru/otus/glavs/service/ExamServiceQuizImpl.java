package ru.otus.glavs.service;

import org.springframework.stereotype.Service;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.helper.ConsoleHelper;
import ru.otus.glavs.service.processor.ExamAnalyzer;


@Service
public class ExamServiceQuizImpl implements ExamService {
    private final ExamAnalyzer examAnalyzer;
    private final StudentService studentService;
    private final ConsoleHelper ch;

    private static final String GREETING_MESSAGE =
            "Starting our exam...\n" +
                    "==========================================\n" +
                    "Examination rules: read question and pick one of three answers\n" +
                    "by typing 1, 2 or 3\n" +
                    System.lineSeparator();


    public ExamServiceQuizImpl(ExamAnalyzer examAnalyzer, StudentService studentService, ConsoleHelper ch) {
        this.examAnalyzer = examAnalyzer;
        this.studentService = studentService;
        this.ch = ch;
    }

    @Override
    public void examine() {
        Student student = studentService.register();
        ch.writeMessage(GREETING_MESSAGE);
        examAnalyzer.


//        Map<Integer, Answer> answerMap = collectAnswers();
//        printExamResults(student, answerMap);
    }


//    private Map<Integer, Answer> collectAnswers() {
////        Map<Integer, Answer> answerMap = new TreeMap<>();
////        List<Quiz> questionList = quizService.getQuestionList();
////        for (Quiz question :
////                questionList) {
////            quizService.displayQuestion(question);
////            int answer = ch.readIntWithPrompt("Please specify your answer(1, 2 or 3):");
////            while (answer < 1 || answer > 3) {
////                answer = ch.readIntWithPrompt("Incorrect answer number, please retry (1, 2 or 3):");
////            }
////            boolean isCorrect = (answer == question.getCorrectAnswer());
////            answerMap.put(question.getId(), new Answer(answer, isCorrect));
////        }
////        return answerMap;
//    }
//
//    private void printMistakes(Map<Integer, Answer> answerMap) {
//        ch.writeMessage("Incorrect answers:");
//        for (Map.Entry<Integer, Answer> entry :
//                answerMap.entrySet()) {
//            if (!entry.getValue().isCorrect) {
//                int questionNumber = entry.getKey();
//                Quiz question = quizService.getQuestionById(questionNumber);
//                String questionText = question.getQuestion();
//                String givenAnswer = quizService.getAnswerByNumber(question, entry.getValue().variant);
//                String correctAnswer = quizService.getAnswerByNumber(question, question.getCorrectAnswer());
//                ch.write(String.format("Answer on question %d: \"%s\" was %s, but correct answer is %s%n",
//                        questionNumber, questionText, givenAnswer, correctAnswer));
//            }
//        }
//    }
//
//
//    private void printExamResults(Student student, Map<Integer, Answer> answerMap) {
//        ch.writeMessage("==========================================" + System.lineSeparator());
//        ch.writeMessage("Exam results review:");
//        int correctAnswerCount = (int) answerMap.values().stream().filter(ans -> ans.isCorrect).count();
//        boolean isPassed = (correctAnswerCount >= minCorrectAnswersCount);
//        String examResult = isPassed ? "Exam is passed" : "Exam is not passed";
//        ch.writeMessage(examResult);
//        ch.write(String.format("Student %s %s correctly answered %d questions of 5", student.getName(), student.getSurname(), correctAnswerCount));
//        ch.writeMessage(System.lineSeparator());
//        if (correctAnswerCount < 5) {
//            printMistakes(answerMap);
//        }
//    }

}
