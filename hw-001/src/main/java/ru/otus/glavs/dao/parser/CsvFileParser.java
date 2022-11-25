package ru.otus.glavs.dao.parser;

import ru.otus.glavs.dao.loader.Loader;
import ru.otus.glavs.domain.Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvFileParser implements Parser{
    private static final int ID = 0;
    private static final int QUESTION = 1;
    private static final int ANSWER1 = 2;
    private static final int ANSWER2 = 3;
    private static final int ANSWER3 = 4;

    private Loader loader;

    @Override
    public List<Quiz> parse() {
        List<Quiz> result = new ArrayList<>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema()
                .withColumnSeparator(';');


        try {
            MappingIterator<List<String>> iterator = mapper
                    .readerForListOf(String.class)
                    .with(com.fasterxml.jackson.dataformat.csv.CsvParser.Feature.WRAP_AS_ARRAY)
                    .with(schema)
                    .readValues(loader.getRowData());
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

    public void setLoader(Loader loader) {
        this.loader = loader;
    }
}
