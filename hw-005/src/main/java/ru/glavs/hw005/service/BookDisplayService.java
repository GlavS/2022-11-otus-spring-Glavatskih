package ru.glavs.hw005.service;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.dao.BookDao;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.ioservice.IOService;

import java.util.List;

@Service
public class BookDisplayService implements DisplayService<Book> {
    private final BookDao bookDao;
    private final IOService ioService;

    public BookDisplayService(BookDao bookDao, IOService ioService) {
        this.bookDao = bookDao;
        this.ioService = ioService;
    }

    @Override
    public void displayList(List<Book> list) {
        printBookList(list);
    }

    @Override
    public void displayItem(Book item) {

    }

    @Override
    public void displayAll() {
        List<Book> books = bookDao.getAll();
        printBookList(books);
    }

    private void printBookList(List<Book> books) {
        for (Book book : books) {
            ioService.printf("Author: %s%s, title: %s, genre: %s%n",
                    book.getAuthor().getInitials(),
                    book.getAuthor().getSurname(),
                    book.getTitle(),
                    book.getGenre().getGenre());
        }
    }
}
