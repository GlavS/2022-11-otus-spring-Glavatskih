package ru.glavs.hw016.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw016.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
