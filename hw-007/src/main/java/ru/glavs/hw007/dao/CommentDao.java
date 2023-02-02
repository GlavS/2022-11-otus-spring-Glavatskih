package ru.glavs.hw007.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw007.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {
}
