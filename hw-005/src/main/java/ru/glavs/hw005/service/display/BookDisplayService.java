package ru.glavs.hw005.service.display;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.io.IOService;

@Service
public class BookDisplayService extends AbstractTableFormat<Book> implements DisplayService<Book> {
    private static final String ITEM_FORMAT_STRING = "|%-5d| %-5s%-15s| %-40s| %-15s|%n";

    public BookDisplayService(IOService ioService) {
        super(ioService);
        super.delimiter = "----------------------------------------------------------------------------------------";
        super.formatString = "|%-5s| %-20s| %-40s| %-15s|%n";
        super.formatArgs = new Object[]{" ID ", "     АВТОР", "          ЗАГЛАВИЕ", "   ЖАНР"};
    }

    @Override
    protected void displayItem(Book book) {
        ioService.printf(ITEM_FORMAT_STRING,
                book.getId(),
                book.getAuthor().getInitials(),
                book.getAuthor().getSurname(),
                book.getTitle(),
                book.getGenre().getGenre());
    }
}
