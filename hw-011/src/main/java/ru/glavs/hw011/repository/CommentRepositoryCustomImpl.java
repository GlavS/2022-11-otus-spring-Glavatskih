package ru.glavs.hw011.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import reactor.core.publisher.Flux;
import ru.glavs.hw011.domain.Book;
import ru.glavs.hw011.domain.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
@AllArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final ReactiveMongoTemplate mongoTemplate;
    public Flux<Comment> updateComments(Book book){
        Aggregation aggregation = newAggregation(
                match(Criteria.where("_id").is(book.getId())),
                lookup("comments", "_id", "commentedBook._id", "comments"),
                project("comments"),
                unwind("comments"),
                project("comments._id", "comments.text", "comments.authorNick", "comments.date", "comments.commentedBook")
        );
        return mongoTemplate
                .aggregate(aggregation, "books", Comment.class)
                .doOnNext(comment -> comment.setCommentedBook(book))
                .doOnNext(mongoTemplate::save);
    }
}
