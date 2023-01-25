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

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


    private static final Book FIRST_BOOK = new Book(FIRST_AUTHOR, FIRST_GENRE, "Книга1");
    private static final Book SECOND_BOOK = new Book(FIRST_AUTHOR, FIRST_GENRE, "Книга2");
    private static final Book THIRD_BOOK = new Book(SECOND_AUTHOR, FIRST_GENRE, "Книга3");
    private static final Book FOURTH_BOOK = new Book(SECOND_AUTHOR, SECOND_GENRE, "Книга4");
    private static final Book NEW_BOOK = new Book(SECOND_AUTHOR, SECOND_GENRE, "Новая книга");

    private static final List<Book> BOOK_LIST = List.of(FIRST_BOOK, SECOND_BOOK, THIRD_BOOK, FOURTH_BOOK);
    private static final int BOOK_LIST_SIZE = 4;
    private static final int FIRST_BOOK_INDEX = 1;

    static {
        try {
            COMMENT_LIST = List.of(
                    new Comment("Comment1, comment1 comment1 comment1 comment1.", "commentator1", new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-19"), FIRST_BOOK),
                    new Comment("Comment2, comment2 comment2 comment2 comment2.", "commentator2", new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-17"), FIRST_BOOK)
            );
        } catch (ParseException e) {
            throw new RuntimeException("Date parse error: " + e.getMessage(), e);
        }
    }

    @Autowired
    EntityManager em;

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
        List<Book> bookList = new ArrayList<>();
        for (int i = 1; i <= BOOK_LIST_SIZE + 1; i++) {
            bookList.add(em.find(Book.class, i));
        }
        assertThat(bookList)
                .hasSize(5)
                .containsExactlyInAnyOrder(FIRST_BOOK, SECOND_BOOK, THIRD_BOOK, FOURTH_BOOK, NEW_BOOK);
    }

    @Test
    @DisplayName("удалять книгу с указанным id")
    void deleteMethodShouldDeleteBookByItsID() {
        dao.delete(FIRST_BOOK_INDEX);
        em.flush();
        List<Book> bookList = new ArrayList<>();
        for (int i = 2; i <= BOOK_LIST_SIZE; i++) {
            bookList.add(em.find(Book.class, i));
        }
        assertThat(bookList).hasSize(3).containsExactlyInAnyOrder(SECOND_BOOK, THIRD_BOOK, FOURTH_BOOK);
    }

    @Test
    @DisplayName("посчитать все имеющиеся книги")
    void countMethodShouldReturnExpectedBooksNumber() {
        long count = dao.count();
        assertThat(count).isEqualTo(BOOK_LIST_SIZE);
    }
}