package ru.otus.glavs.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.helper.ConsoleHelperImpl;
import ru.otus.glavs.service.processor.ExamAnalyzer;
import ru.otus.glavs.service.processor.ExamAnalyzerImpl;
import ru.otus.glavs.service.processor.ExamProcessor;
import ru.otus.glavs.service.processor.ExamProcessorImpl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("ExamServiceQuizImpl class")
class ExamServiceQuizImplTest {
    private final ExamProcessor examProcessor = mock(ExamProcessorImpl.class);
    private final ExamAnalyzer examAnalyzer = mock(ExamAnalyzerImpl.class);

    private final StudentService studentService = mock(StudentService.class);
    private final ConsoleHelperImpl ch = mock(ConsoleHelperImpl.class);
    @Test
    @DisplayName("examine method should invoke helpers")
    void examineTest() {
        ExamService exam = new ExamServiceQuizImpl(examProcessor, examAnalyzer, studentService, ch);
        when(studentService.register()).thenReturn(new Student(1, "A", "B"));
        exam.examine();

        verify(studentService, atLeast(1)).register();
        verify(examProcessor, atLeast(1)).collectAnswers();
        verify(examAnalyzer, atLeast(1)).printExamResults(any(), any());
        verify(ch, atLeast(1)).writeMessage(anyString());
    }
}