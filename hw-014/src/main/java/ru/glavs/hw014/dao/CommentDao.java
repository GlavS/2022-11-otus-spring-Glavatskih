package ru.glavs.hw014.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw014.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
