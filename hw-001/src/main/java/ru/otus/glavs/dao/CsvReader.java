package ru.otus.glavs.dao;

import org.springframework.core.io.ClassPathResource;
import ru.otus.glavs.domain.Quiz;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class CsvReader {
    private static final int ID = 0;
    private static final int QUESTION = 1;
    private static final int ANSWER1 = 2;
    private static final int ANSWER2 = 3;
    private static final int ANSWER3 = 4;
    private String quizCsvFileName; // property, hardcoded in spring-context.xml

    public List<Quiz> readAllRows() {
        List<Quiz> result = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource(quizCsvFileName); //get Quiz resource
        //We are to use jackson-dataformat-csv library to parse Quiz resource
        //https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv
        //https://cowtowncoder.medium.com/reading-csv-with-jackson-c4e74a15ddc1
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema()
                .withColumnSeparator(';');

        try (InputStream is = resource.getInputStream()) {
            MappingIterator<List<String>> iterator = mapper
                    .readerForListOf(String.class)
                    .with(CsvParser.Feature.WRAP_AS_ARRAY)
                    .with(schema)
                    .readValues(is);
            while (iterator.hasNextValue()) {
                List<String> row = iterator.nextValue();
                Quiz quiz = new Quiz(
                        Integer.parseInt(row.get(ID)), // We don't use ID for now, reserving for future tasks
                        row.get(QUESTION),
                        row.get(ANSWER1),
                        row.get(ANSWER2),
                        row.get(ANSWER3));
                result.add(quiz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setQuizCsvFileName(String quizCsvFileName) { //property setter
        this.quizCsvFileName = quizCsvFileName;
    }
}
