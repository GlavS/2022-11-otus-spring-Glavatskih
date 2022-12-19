package ru.otus.glavs.service.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.glavs.domain.Quiz;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.QuizService;
import ru.otus.glavs.service.helper.ConsoleHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("In class ExamProcessorImpl")
@ExtendWith(MockitoExtension.class)
class ExamProcessorImplTest {
    @Mock
    private ConsoleHelper ch;
    @Mock
    private QuizService quizService;
    @Mock
    private LocalizedMessages storage;
    private List<Quiz> testList;
    private Map<Integer, Answer> testMap;


    @BeforeEach
    void setUp() {
        testList = new ArrayList<>();
        testList.add(new Quiz(1, "Q", "A1", "A2", "A3", 1));
        testMap = new TreeMap<>();
        testMap.put(1, new Answer(1, true));
    }

    @Test
    @DisplayName("collectAnswers method has to return Map<Integer, Answer>")
    void collectAnswersMethodShouldReturnCorrectMap() {
        when(quizService.getQuestionList()).thenReturn(testList);
        when(storage.getText(anyString())).thenReturn("Prompt");
        when(ch.readIntWithPrompt(anyString())).thenReturn(1);
        ExamProcessor processor = new ExamProcessorImpl(ch, quizService, storage);

        Map<Integer, Answer> answerMap = processor.collectAnswers();
        assertThat(answerMap).usingRecursiveComparison().isEqualTo(testMap);
    }
}