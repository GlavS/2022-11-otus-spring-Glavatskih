package ru.glavs.hw011.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import reactor.core.publisher.Flux;
import ru.glavs.hw011.domain.Book;
import ru.glavs.hw011.domain.projections.BookWithComments;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@AllArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final ReactiveMongoTemplate mongoTemplate;


    @Override
    public Flux<BookWithComments> findAllWithComments() {
        Aggregation aggregation = newAggregation(
                lookup("comments", "_id", "commentedBook._id", "comments")
        );
        return mongoTemplate.aggregate(aggregation, Book.class, BookWithComments.class);
    }


    public Flux<BookWithComments> findBookWithCommentsById(String id) {
        Aggregation aggregation = newAggregation(
                lookup("comments", "_id", "commentedBook._id", "comments"),
                match(where("_id").is(id))
        );
        return mongoTemplate.aggregate
                (aggregation, Book.class, BookWithComments.class);
    }
}
