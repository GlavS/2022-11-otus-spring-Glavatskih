package ru.otus.glavs.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.helper.ConsoleHelperImpl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("ExamServiceQuizImpl class")
class ExamServiceQuizImplTest {

    private final QuizService quizService = mock(QuizService.class);
    private final StudentService studentService = mock(StudentService.class);
    private final ConsoleHelperImpl ch = mock(ConsoleHelperImpl.class);
    @Test
    @DisplayName("examine method should invoke helpers")
    void examineTest() {
        ExamService exam = new ExamServiceQuizImpl(quizService, studentService, ch);
        when(studentService.register()).thenReturn(new Student(1, "A", "B"));
        exam.examine();

        verify(studentService, atLeast(1)).register();
        verify(quizService, atLeast(1)).getQuestionList();
        verify(ch, atLeast(1)).writeMessage(anyString());
    }
}