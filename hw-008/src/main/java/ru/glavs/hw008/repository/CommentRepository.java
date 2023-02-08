package ru.glavs.hw008.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.glavs.hw008.domain.Comment;

public interface CommentDao extends MongoRepository<Comment, String> {

}
