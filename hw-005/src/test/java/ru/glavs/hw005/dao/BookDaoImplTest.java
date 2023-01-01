package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import({BookDaoImpl.class, AuthorDaoImpl.class})
@DisplayName("В классе BookDaoImpl")
class BookDaoImplTest {

    @Autowired
    private BookDaoImpl bookDao;

    @Test
    @DisplayName("метод getAll должен вернуть все книги")
    void getAllMethodShouldReturnAllBooks() {
        assertThat(bookDao.getAll()).isInstanceOf(List.class);
        assertThat(bookDao.getAll().size()).isEqualTo(4);
        assertThat(bookDao.getAll().get(0)).isInstanceOf(Book.class);
    }

}