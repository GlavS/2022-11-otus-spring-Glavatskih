package ru.otus.glavs.service.processor;

import org.springframework.stereotype.Component;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.service.QuizService;
import ru.otus.glavs.service.ioservice.LocalizedIOService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class ExamProcessorImpl implements ExamProcessor {

    private final LocalizedIOService ioService;
    private final QuizService quizService;

    public ExamProcessorImpl(LocalizedIOService ioService, QuizService quizService) {
        this.ioService = ioService;
        this.quizService = quizService;
    }

    @Override
    public Map<Integer, Answer> collectAnswers() {
        Map<Integer, Answer> answerMap = new TreeMap<>();
        List<Quiz> questionList = quizService.getQuestionList();
        for (Quiz question :
                questionList) {
            quizService.displayQuestion(question);
            int answer = ioService.readIntWithPrompt("examprocessor.collectanswers.prompt");
            while (isNotValid(answer)) {
                answer = ioService.readIntWithPrompt("examprocessor.collectanswers.invalid");
            }
            boolean isCorrect = (answer == question.getCorrectAnswer());
            answerMap.put(question.getId(), new Answer(answer, isCorrect));
        }
        return answerMap;
    }

    private boolean isNotValid(int answer) {
        return (answer < 1 || answer > 3);
    }
}
