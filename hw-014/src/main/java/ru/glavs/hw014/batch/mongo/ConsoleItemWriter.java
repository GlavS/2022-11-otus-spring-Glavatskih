package ru.glavs.hw014.batch.mongo;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleItemWriter<T> implements ItemWriter<T> {
    @Override
    public void write(List<? extends T> list){
        for (T item : list) {
            System.out.println(item);
        }
    }
}
