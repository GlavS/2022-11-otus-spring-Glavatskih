package ru.glavs.hw013.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw013.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
