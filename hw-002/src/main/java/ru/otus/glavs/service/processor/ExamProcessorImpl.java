package ru.otus.glavs.service.processor;

import org.springframework.stereotype.Component;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.QuizService;
import ru.otus.glavs.service.helper.ConsoleHelper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class ExamProcessorImpl implements ExamProcessor {

    private final ConsoleHelper ch;
    private final QuizService quizService;

    public ExamProcessorImpl(ConsoleHelper ch, QuizService quizService) {
        this.ch = ch;
        this.quizService = quizService;
    }

    @Override
    public Map<Integer, Answer> collectAnswers() {
        Map<Integer, Answer> answerMap = new TreeMap<>();
        List<Quiz> questionList = quizService.getQuestionList();
        for (Quiz question :
                questionList) {
            quizService.displayQuestion(question);
            int answer = ch.readIntWithPrompt("Please specify your answer(1, 2 or 3):");
            while (answer < 1 || answer > 3) {
                answer = ch.readIntWithPrompt("Incorrect answer number, please retry (1, 2 or 3):");
            }
            boolean isCorrect = (answer == question.getCorrectAnswer());
            answerMap.put(question.getId(), new Answer(answer, isCorrect));
        }
        return answerMap;
    }
}
