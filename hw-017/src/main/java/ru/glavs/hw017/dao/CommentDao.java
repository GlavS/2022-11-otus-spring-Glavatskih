package ru.glavs.hw017.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.glavs.hw017.domain.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
