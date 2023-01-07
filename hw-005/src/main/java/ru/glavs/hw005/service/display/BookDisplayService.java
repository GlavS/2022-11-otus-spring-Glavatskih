package ru.glavs.hw005.service.display;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.io.IOService;

import java.util.List;

@Service
public class BookDisplayService extends AbstractTableFormat<Book> implements DisplayService<Book> {
    private static final String DELIMITER = "----------------------------------------------------------------------------------------";
    private static final String HEADER_FORMAT_STRING = "|%-5s| %-20s| %-40s| %-15s|%n";
    private static final Object[] FORMAT_ARGS = new Object[]{" ID ", "     АВТОР", "          ЗАГЛАВИЕ", "   ЖАНР"};

    public BookDisplayService(IOService ioService) {
        super(ioService);
    }

    public void printOne(Book item) {
        displayHeader(DELIMITER, HEADER_FORMAT_STRING, FORMAT_ARGS);
        displayItem(item);
        displayFooter(DELIMITER);
    }


    public void printList(List<Book> books) {
        displayHeader(DELIMITER, HEADER_FORMAT_STRING, FORMAT_ARGS);
        books.forEach(this::displayItem);
        displayFooter(DELIMITER);
    }

    @Override
    protected void displayItem(Book book) {
        ioService.printf("|%-5d| %-5s%-15s| %-40s| %-15s|%n",
                book.getId(),
                book.getAuthor().getInitials(),
                book.getAuthor().getSurname(),
                book.getTitle(),
                book.getGenre().getGenre());
    }
}
