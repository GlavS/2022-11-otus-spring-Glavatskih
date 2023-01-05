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
    public void displayItem(int id) {
        Book book = bookDao.getById(id);
        printHeader();
        printBook(book);
        printFooter();
    }

    @Override
    public void displayAll() {
        List<Book> books = bookDao.getAll();
        printBookList(books);
    }

    private void printBookList(List<Book> books) {
        printHeader();
        books.forEach(this::printBook);
        printFooter();
    }
    private void printHeader(){
        ioService.println("----------------------------------------------------------------------------------------");
        ioService.printf("|%-5s| %-20s| %-40s| %-15s|%n"," ID ", "     АВТОР", "          ЗАГЛАВИЕ", "   ЖАНР");
        ioService.println("----------------------------------------------------------------------------------------");
    }
    private void printFooter(){
        ioService.println("----------------------------------------------------------------------------------------");
    }
    private void printBook(Book book){
        ioService.printf("|%4d | %-5s%-15s| %-40s| %-15s|%n",
                book.getId(),
                book.getAuthor().getInitials(),
                book.getAuthor().getSurname(),
                book.getTitle(),
                book.getGenre().getGenre());
    }
}
