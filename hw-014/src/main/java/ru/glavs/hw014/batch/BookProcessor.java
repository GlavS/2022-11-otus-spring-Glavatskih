package ru.glavs.hw014.batch;

import org.bson.types.ObjectId;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.glavs.hw014.batch.mongo.MongoBook;
import ru.glavs.hw014.batch.mongo.MongoComment;
import ru.glavs.hw014.domain.Book;

import java.util.List;

@Component
public class BookProcessor implements ItemProcessor<Book, MongoBook> {
    @Override
    public MongoBook process(Book book) {
        List<MongoComment> commentList = book.getComments()
                .stream()
                .map(comment ->
                        new MongoComment(
                                new ObjectId(),
                                comment.getText(),
                                comment.getAuthorNick(),
                                comment.getDate()))
                .toList();
        return new MongoBook(
                new ObjectId(),
                book.getAuthor(),
                book.getGenre(),
                book.getTitle(),
                commentList
        );
    }
}
