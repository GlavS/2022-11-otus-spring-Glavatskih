package ru.glavs.hw008.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
@AllArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final MongoTemplate mongoTemplate;
    public List<Comment> updateComments(Book book){
        List<Comment> result = new ArrayList<>();
        Aggregation aggregation = newAggregation(
                match(Criteria.where("_id").is(book.getId())),
                lookup("comments", "_id", "commentedBook._id", "comments"),
                project("comments"),
                unwind("comments"),
                project("comments._id", "comments.text", "comments.authorNick", "comments.date", "comments.commentedBook")
        );
        List<Comment> commentList = mongoTemplate.aggregate(aggregation, "books", Comment.class).getMappedResults();
        commentList.forEach(comment -> comment.setCommentedBook(book));
        for (Comment c : commentList) {
            result.add(mongoTemplate.save(c));
        }

        return result;
    }
}
