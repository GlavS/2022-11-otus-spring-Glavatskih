package ru.otus.glavs.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.glavs.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@DisplayName("В классе StudentDaoImpl")
class StudentDaoImplTest {

    @Autowired
    StudentDaoImpl dao;

    @Test
    @DisplayName("метод registerNew должен вернуть объект Student")
    void registerNewMethodShouldCorrectlyReturnStudent() {
        assertThat(dao.registerNew("Ivan", "Petrov")).isInstanceOf(Student.class);
    }

    @Configuration
    @Import(StudentDaoImpl.class)
    public static class NestedConfig {

    }

    @Test
    void registerNewMethodReturnedObjectShouldWorkCorrectly(){
        assertThat(dao.registerNew("Ivan", "Petrov"))
                .returns("Ivan", from(Student::getName))
                .returns("Petrov", from(Student::getSurname));
    }

}