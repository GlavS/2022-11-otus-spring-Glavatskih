package ru.glavs.hw008.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
