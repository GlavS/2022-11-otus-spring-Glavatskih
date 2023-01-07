package ru.glavs.hw005.service.display;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.io.IOService;

import java.util.List;

@Service
public class AuthorDisplayService  extends AbstractTableFormat<Author> implements DisplayService<Author>{

    private static final String DELIMITER = "---------------------------------------------------------------";
    private static final String HEADER_FORMAT_STRING = "|%5s| %-30s| %-15s| %-5s|%n";
    private static final String ITEM_FORMAT_STRING   = "|%-5d| %-30s| %-15s| %-5s|%n";
    private static final Object[] FORMAT_ARGS = new Object[]{" ID ", "          ИМЯ", "    ФАМИЛИЯ ", "И.О."};
    public AuthorDisplayService(IOService ioService) {
        super(ioService);
    }

    @Override
    public void printOne(Author item) {
        displayHeader(DELIMITER, HEADER_FORMAT_STRING, FORMAT_ARGS);
        displayItem(item);
        displayFooter(DELIMITER);
    }

    @Override
    public void printList(List<Author> itemList) {
        displayHeader(DELIMITER, HEADER_FORMAT_STRING, FORMAT_ARGS);
        itemList.forEach(this::displayItem);
        displayFooter(DELIMITER);
    }

    @Override
    protected void displayItem(Author author) {
        ioService.printf(ITEM_FORMAT_STRING,
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getInitials());
    }
}
