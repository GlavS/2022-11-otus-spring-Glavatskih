package ru.otus.glavs.service.processor;

import org.springframework.stereotype.Component;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.l10n.LocalizedExamProcessorMessagesStorage;
import ru.otus.glavs.service.QuizService;
import ru.otus.glavs.service.helper.ConsoleHelper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class ExamProcessorImpl implements ExamProcessor {

    private final ConsoleHelper ch;
    private final QuizService quizService;
    private final LocalizedExamProcessorMessagesStorage storage;

    public ExamProcessorImpl(ConsoleHelper ch, QuizService quizService, LocalizedExamProcessorMessagesStorage storage) {
        this.ch = ch;
        this.quizService = quizService;
        this.storage = storage;
    }

    @Override
    public Map<Integer, Answer> collectAnswers() {
        Map<Integer, Answer> answerMap = new TreeMap<>();
        List<Quiz> questionList = quizService.getQuestionList();
        for (Quiz question :
                questionList) {
            quizService.displayQuestion(question);
            int answer = ch.readIntWithPrompt(storage.getCollectAnswersPrompt());
            while (isNotValid(answer)) {
                answer = ch.readIntWithPrompt(storage.getCollectAnswersInvalid());
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
