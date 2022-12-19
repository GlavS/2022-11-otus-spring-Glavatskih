package ru.otus.glavs.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.helper.ConsoleHelperImpl;
import ru.otus.glavs.service.processor.ExamAnalyzer;
import ru.otus.glavs.service.processor.ExamProcessor;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("ExamServiceQuizImpl class")
@ExtendWith(MockitoExtension.class)
class ExamServiceQuizImplTest {
    @Mock
    private ExamProcessor examProcessor;
    @Mock
    private ExamAnalyzer examAnalyzer;
    @Mock

    private StudentService studentService;
    @Mock
    private ConsoleHelperImpl ch;
    @Mock
    private LocalizedMessages storage;

    @Test
    @DisplayName("examine method should invoke helpers")
    void examineTest() {
        ExamService exam = new ExamServiceQuizImpl(examProcessor, examAnalyzer, studentService, ch, storage);
        when(studentService.register()).thenReturn(new Student(1, "A", "B"));
        when(storage.getText(anyString())).thenReturn("Greeting message");
        exam.examine();

        verify(studentService, atLeast(1)).register();
        verify(examProcessor, atLeast(1)).collectAnswers();
        verify(examAnalyzer, atLeast(1)).printExamResults(any(), any());
        verify(ch, atLeast(1)).writeMessage(anyString());
    }
}