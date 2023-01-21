package ru.glavs.hw005.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;
import ru.glavs.hw005.domain.Genre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Класс BookDao должен")
@Import(BookDaoImpl.class)
class BookDaoImplTest {
    private static final Author FIRST_AUTHOR = new Author(1, "Имя1", "Фамилия1", "А.А.");
    private static final Author SECOND_AUTHOR = new Author(2, "Имя2", "Фамилия2", "Б.Б.");
    private static final Genre FIRST_GENRE = new Genre(1, "Жанр1");
    private static final Genre SECOND_GENRE = new Genre(2, "Жанр2");
    private static final List<Comment> COMMENT_LIST;

    static {
        try {
            COMMENT_LIST = List.of(
                    new Comment(1, "Comment1, comment1 comment1 comment1 comment1.", "commentator1", new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-19")),
                    new Comment(2, "Comment2, comment2 comment2 comment2 comment2.", "commentator2", new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-17"))
            );
        } catch (ParseException e) {
            throw new RuntimeException("Date parse error: " + e.getMessage(), e);
        }
    }

    private static final Book FIRST_BOOK = new Book(1, FIRST_AUTHOR, FIRST_GENRE, "Книга1", COMMENT_LIST);
    private static final Book SECOND_BOOK = new Book(2, FIRST_AUTHOR, FIRST_GENRE, "Книга2", List.of());
    private static final Book THIRD_BOOK = new Book(3, SECOND_AUTHOR, FIRST_GENRE, "Книга3", List.of());
    private static final Book FOURTH_BOOK = new Book(4, SECOND_AUTHOR, SECOND_GENRE, "Книга4", List.of());
    private static final Book NEW_BOOK = new Book(5, SECOND_AUTHOR, SECOND_GENRE, "Новая книга", List.of());

    private static final List<Book> BOOK_LIST = List.of(FIRST_BOOK, SECOND_BOOK, THIRD_BOOK, FOURTH_BOOK);
    private static final int BOOK_LIST_SIZE = 4;
    private static final int FIRST_BOOK_INDEX = 1;


    @Autowired
    private BookDaoImpl dao;

    @Test
    @DisplayName("возвращать список всех книг")
    void getAllMethodShouldReturnListOfAllBooks() {
        List<Book> bookList = dao.getAll();
        assertThat(bookList).hasSize(BOOK_LIST_SIZE).usingRecursiveComparison().isEqualTo(BOOK_LIST);
    }

    @Test
    @DisplayName("возвращать книгу по её id")
    void getByIdMethodShouldReturnBookByItsID() {
        Book book = dao.getById(FIRST_BOOK_INDEX);
        assertThat(book).usingRecursiveComparison().isEqualTo(FIRST_BOOK);
    }

    @Test
    @DisplayName("находить книгу или список книг по части названия")
    void findByTitlePatternMethodShouldReturnExpectedBookList() {
        List<Book> foundBooks = dao.findByTitlePattern("Книга");
        assertThat(foundBooks)
                .hasSize(4)
                .containsExactlyInAnyOrder(FIRST_BOOK, SECOND_BOOK, THIRD_BOOK, FOURTH_BOOK);
        foundBooks = dao.findByTitlePattern("Книга4");
        assertThat(foundBooks)
                .hasSize(1)
                .containsExactlyInAnyOrder(FOURTH_BOOK);
    }

    @Test
    @DisplayName("возвращать список книг указанного автора")
    void findByAuthorMethodShouldReturnExpectedBookList() {
        List<Book> foundBooks = dao.findByAuthor(FIRST_AUTHOR);
        assertThat(foundBooks)
                .hasSize(2)
                .containsExactlyInAnyOrder(FIRST_BOOK, SECOND_BOOK);
    }

    @Test
    @DisplayName("возвращать список книг указанного жанра")
    void findByGenreMethodShouldReturnExpectedBookList() {
        List<Book> foundBooks = dao.findByGenre(FIRST_GENRE);
        assertThat(foundBooks)
                .hasSize(3)
                .containsExactlyInAnyOrder(FIRST_BOOK, SECOND_BOOK, THIRD_BOOK);
    }

    @Test
    @DisplayName("сохранять в БД новую книгу")
    void saveMethodShouldSaveNewBookToDatabase() {
        dao.save(NEW_BOOK);
        List<Book> bookList = dao.getAll();
        assertThat(bookList)
                .hasSize(5)
                .containsExactlyInAnyOrder(FIRST_BOOK, SECOND_BOOK, THIRD_BOOK, FOURTH_BOOK, NEW_BOOK);
    }

    @Test
    @DisplayName("удалять книгу с указанным id")
    void deleteMethodShouldDeleteBookByItsID() {
        dao.delete(FIRST_BOOK_INDEX);
        List<Book> bookList = dao.getAll();
        assertThat(bookList).hasSize(3).containsExactlyInAnyOrder(SECOND_BOOK, THIRD_BOOK, FOURTH_BOOK);
    }

    @Test
    @DisplayName("посчитать все имеющиеся книги")
    void countMethodShouldReturnExpectedBooksNumber() {
        long count = dao.count();
        assertThat(count).isEqualTo(BOOK_LIST_SIZE);
    }
}