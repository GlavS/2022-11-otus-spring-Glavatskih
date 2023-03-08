package ru.glavs.hw008.service.CRUD;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.repository.CommentRepository;

import java.util.List;

@Service
public class CommentCRUDImpl implements CommentCRUD {

    private final CommentRepository repository;

    public CommentCRUDImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Comment comment) {
        repository.delete(comment);
    }

    @Override
    public void deleteAll(List<Comment> commentList) {
        repository.deleteAll(commentList);
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public List<Comment> findCommentsByBook(String bookId) {
        return repository.findAllByCommentedBookId(bookId);
    }

    @Override
    public List<Comment> findByCommentText(String partOfText) {
        return repository.findByTextContainingIgnoreCase(partOfText);
    }

    @Override
    public Comment findById(String id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
