package ru.glavs.hw008.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, ObjectId> {
    List<Comment> findAllByCommentedBookId(ObjectId id);
    List<Comment> findByTextContainingIgnoreCase(String partOfText);
}
