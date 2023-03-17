package ru.glavs.hw011.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.glavs.hw011.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String>{

}
