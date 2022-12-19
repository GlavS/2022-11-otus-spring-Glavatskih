package ru.otus.glavs.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.glavs.dao.StudentDao;
import ru.otus.glavs.domain.Student;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.helper.ConsoleHelperImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("StudentServiceImpl class")
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    private StudentDao dao;
    @Mock
    private ConsoleHelperImpl ch;
    @Mock
    private LocalizedMessages storage;

    @Test
    @DisplayName("register() method should return Student object instance")
    void registerMethodShouldReturnStudentInstance() {
        StudentServiceImpl studentService = new StudentServiceImpl(dao, ch, storage);
        when(storage.getText(anyString())).thenReturn("First name prompt");
        when(storage.getText(anyString())).thenReturn("Last name prompt");

        String name = "Name";
        String surname = "Surname";
        when(ch.readStringWithPrompt(anyString())).thenReturn(name);
        when(dao.registerNew(anyString(), anyString())).thenReturn(new Student(1, name, surname));

        Student student = studentService.register();
        assertThat(student).isInstanceOf(Student.class);
    }
}