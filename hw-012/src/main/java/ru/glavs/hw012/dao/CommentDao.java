package ru.glavs.hw012.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw012.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
