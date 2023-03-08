package ru.glavs.hw008.repository;

import com.mongodb.lang.NonNullApi;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import ru.glavs.hw008.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryCustom {
    List<Comment> findAllByCommentedBookId(String id);

    List<Comment> findByTextContainingIgnoreCase(String partOfText);

    @Override
    void deleteById(@NonNull String id);
}
