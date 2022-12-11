package ru.otus.glavs.dao;

import ru.otus.glavs.domain.Student;

public interface StudentDao {
    Student registerNew(String name, String surname);
}
