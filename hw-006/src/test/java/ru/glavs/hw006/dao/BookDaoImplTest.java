package ru.glavs.hw006.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.glavs.hw006.domain.Author;
import ru.glavs.hw006.domain.Book;
import ru.glavs.hw006.domain.Genre;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Класс BookDao должен")
@Import(BookDaoImpl.class)
class BookDaoImplTest {
    private static final int BOOK_LIST_SIZE = 4;
    private static final long FIRST_BOOK_INDEX = 1;

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
        Author authorToFind = em.find(Author.class, 1L);
        List<Book> foundBooks = dao.findByAuthor(authorToFind);
        assertThat(foundBooks)
                .hasSize(2);
    }

    @Test
    @DisplayName("возвращать список книг указанного жанра")
    void findByGenreMethodShouldReturnExpectedBookList() {
        Genre genreToFind = em.find(Genre.class, 1L);
        List<Book> foundBooks = dao.findByGenre(genreToFind);
        assertThat(foundBooks)
                .hasSize(3);
    }

    @Test
    @DisplayName("сохранять в БД новую книгу")
    void saveMethodShouldSaveNewBookToDatabase() {
        Author authorToAdd = em.find(Author.class, 2L);
        Genre genreToAdd = em.find(Genre.class, 2L);
        dao.save(new Book(authorToAdd, genreToAdd, "Новая книга"));
        em.flush();
        List<Book> bookList = new ArrayList<>();
        for (long i = 1; i <= BOOK_LIST_SIZE + 1; i++) {
            bookList.add(em.find(Book.class, i));
        }
        assertThat(bookList)
                .hasSize(5);
        assertThat(em.find(Book.class, 5L).getTitle()).isEqualTo("Новая книга");
    }

    @Test
    @DisplayName("удалять книгу")
    void deleteMethodShouldDeleteBook() {
        Book bookToDelete = em.find(Book.class, 1L);
        dao.delete(bookToDelete);
        em.flush();
        List<Book> bookList = new ArrayList<>();
        for (long i = 2; i <= BOOK_LIST_SIZE; i++) {
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

    @Test
    @DisplayName("вернуть список книг с непустыми комментариями")
    void getAllWithCommentsOnlyShouldReturnExpectedBookList() {
        List<Book> bookList = dao.getAllWithCommentsOnly();
        assertThat(bookList.size()).isEqualTo(1);
    }
}