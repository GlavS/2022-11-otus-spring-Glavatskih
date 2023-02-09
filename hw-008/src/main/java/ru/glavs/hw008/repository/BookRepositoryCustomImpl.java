package ru.glavs.hw008.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.projections.BookWithComments;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@AllArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<BookWithComments> findAllWithCommentsOnly() {
        MongoExpression commentsNotEmpty = MongoExpression.create("{ $expr:{ $ne: [0, { $size: \"$comments\" }]}}");

        Aggregation aggregation = newAggregation(
                lookup("comments", "_id", "commentedBook._id", "comments"),
                match(AggregationExpression.from(commentsNotEmpty))
        );
        return mongoTemplate.aggregate(aggregation, Book.class, BookWithComments.class).getMappedResults();
    }

    @Override
    public List<BookWithComments> findAllWithComments() {
        Aggregation aggregation = newAggregation(
                lookup("comments", "_id", "commentedBook._id", "comments")
        );
        return mongoTemplate.aggregate(aggregation, Book.class, BookWithComments.class).getMappedResults();
    }

    @Override
    public List<BookWithComments> findAllWithCommentsByTitleContaining(String titlePart) {
        Aggregation aggregation = newAggregation(
                lookup("comments", "_id", "commentedBook._id", "comments"),
                match(where("title").regex("^" + titlePart))
        );
        return mongoTemplate.aggregate(aggregation, Book.class, BookWithComments.class).getMappedResults();
    }
}
