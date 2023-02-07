package ru.glavs.hw008.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw008.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
