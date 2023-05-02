package ru.glavs.hw016.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.glavs.hw016.domain.Author;
import ru.glavs.hw016.domain.Book;
import ru.glavs.hw016.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Класс BookDao должен")
class BookDaoImplTest {
    private static final int BOOK_LIST_SIZE = 4;
    private static final long FIRST_BOOK_INDEX = 1;

    @Autowired
    TestEntityManager em;

    @Autowired
    private BookDao dao;

    @Test
    @DisplayName("возвращать список всех книг")
    void getAllMethodShouldReturnListOfAllBooks() {
        List<Book> bookList = dao.findAll();
        assertThat(bookList).hasSize(BOOK_LIST_SIZE);
    }

    @Test
    @DisplayName("возвращать книгу по её id")
    void getByIdMethodShouldReturnBookByItsID() {
        Book book = dao.getReferenceById(FIRST_BOOK_INDEX);
        assertThat(book.getTitle()).isEqualTo("Книга1");
    }

    @Test
    @DisplayName("находить книгу или список книг по части названия")
    void findByTitlePatternMethodShouldReturnExpectedBookList() {
        List<Book> foundBooks = dao.findByTitleContaining("Книга");
        assertThat(foundBooks)
                .hasSize(4);

        foundBooks = dao.findByTitleContaining("Книга4");
        assertThat(foundBooks)
                .hasSize(1);

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