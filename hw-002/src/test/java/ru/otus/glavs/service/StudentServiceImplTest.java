package ru.otus.glavs.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.glavs.dao.StudentDao;
import ru.otus.glavs.dao.StudentDaoImpl;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.service.helper.ConsoleHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("StudentServiceImpl class")
class StudentServiceImplTest {
    private final StudentDao dao = mock(StudentDaoImpl.class);
    private final ConsoleHelper ch = mock(ConsoleHelper.class);

    @Test
    @DisplayName("register() method should return Student object instance")
    void registerMethodShouldReturnStudentInstance() {
        StudentServiceImpl studentService = new StudentServiceImpl(dao, ch);

        String name = "Name";
        String surname = "Surname";
        when(ch.readStringWithPrompt(anyString())).thenReturn(name);
        when(dao.registerNew(anyString(), anyString())).thenReturn(new Student(1, name, surname));

        Student student = studentService.register();
        assertThat(student).isInstanceOf(Student.class);
    }
}