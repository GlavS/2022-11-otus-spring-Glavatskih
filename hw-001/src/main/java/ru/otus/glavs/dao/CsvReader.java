package ru.otus.glavs.dao;

import ru.otus.glavs.domain.Quiz;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unused")
public class CsvReader {
    private String quizCsvFileName; // property, hardcoded in spring-context.xml
    public List<Quiz> readAllRows(){
        List<Quiz> result = new ArrayList<>();
        return result;
    }

    public void setQuizCsvFileName(String quizCsvFileName) { //property setter
        this.quizCsvFileName = quizCsvFileName;
    }
}
