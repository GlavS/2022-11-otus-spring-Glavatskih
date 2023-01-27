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
    private static final Author FIRST_AUTHOR = new Author("Имя1", "Фамилия1", "А.А.");
    private static final Author SECOND_AUTHOR = new Author("Имя2", "Фамилия2", "Б.Б.");
    private static final Genre FIRST_GENRE = new Genre("Жанр1");
    private static final Genre SECOND_GENRE = new Genre("Жанр2");
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
        assertThat(bookList).hasSize(BOOK_LIST_SIZE);
    }

    @Test
    @DisplayName("возвращать книгу по её id")
    void getByIdMethodShouldReturnBookByItsID() {
        Book book = dao.getById(FIRST_BOOK_INDEX);
        assertThat(book.getTitle()).isEqualTo("Книга1");
    }

    @Test
    @DisplayName("находить книгу или список книг по части названия")
    void findByTitlePatternMethodShouldReturnExpectedBookList() {
        List<Book> foundBooks = dao.findByTitlePattern("Книга");
        assertThat(foundBooks)
                .hasSize(4);

        foundBooks = dao.findByTitlePattern("Книга4");
        assertThat(foundBooks)
                .hasSize(1);

    }

    @Test
    @DisplayName("возвращать список книг указанного автора")
    void findByAuthorMethodShouldReturnExpectedBookList() {
        Author authorToFind = em.find(Author.class, 1);
        List<Book> foundBooks = dao.findByAuthor(authorToFind);
        assertThat(foundBooks)
                .hasSize(2);
    }

    @Test
    @DisplayName("возвращать список книг указанного жанра")
    void findByGenreMethodShouldReturnExpectedBookList() {
        Genre genreToFind = em.find(Genre.class, 1);
        List<Book> foundBooks = dao.findByGenre(genreToFind);
        assertThat(foundBooks)
                .hasSize(3);
    }

    @Test
    @DisplayName("сохранять в БД новую книгу")
    void saveMethodShouldSaveNewBookToDatabase() {
        Author authorToAdd = em.find(Author.class, 2);
        Genre genreToAdd = em.find(Genre.class, 2);
        dao.save(new Book(authorToAdd, genreToAdd, "Новая книга"));
        em.flush();
        List<Book> bookList = new ArrayList<>();
        for (int i = 1; i <= BOOK_LIST_SIZE + 1; i++) {
            bookList.add(em.find(Book.class, i));
        }
        assertThat(bookList)
                .hasSize(5);
        assertThat(em.find(Book.class, 5).getTitle()).isEqualTo("Новая книга");
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
        assertThat(bookList).hasSize(3);
        assertThat(em.find(Book.class, FIRST_BOOK_INDEX)).isNull();
    }

    @Test
    @DisplayName("посчитать все имеющиеся книги")
    void countMethodShouldReturnExpectedBooksNumber() {
        long count = dao.count();
        assertThat(count).isEqualTo(BOOK_LIST_SIZE);
    }
}