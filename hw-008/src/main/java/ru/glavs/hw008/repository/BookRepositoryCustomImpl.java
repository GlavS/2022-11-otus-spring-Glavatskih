package ru.glavs.hw008.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.projections.BookComments;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@AllArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<BookComments> findAllWithComments() {
        Aggregation aggregation = newAggregation(
                lookup("comments", "_id", "commentedBook._id", "comments"),
                match(AggregationExpression.from(MongoExpression.create("{ $expr:{ $ne: [0, { $size: \"$comments\" }]}}")))
        );

        return mongoTemplate.aggregate(aggregation, Book.class, BookComments.class).getMappedResults();
    }

    @Override
    public List<BookComments> findAllWithCommentsOnly() {
        Aggregation aggregation = newAggregation(
                lookup("comments", "_id", "commentedBook._id", "comments")
        );

        return mongoTemplate.aggregate(aggregation, Book.class, BookComments.class).getMappedResults();
    }
}
