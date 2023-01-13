package ru.glavs.hw005.service.display;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Author;
import ru.glavs.hw005.io.IOService;

@Service
public class AuthorDisplayService extends AbstractDisplayService<Author> implements DisplayService<Author> {

    private static final String ITEM_FORMAT_STRING = "|%-5d| %-30s| %-15s| %-5s|%n";

    public AuthorDisplayService(IOService ioService) {
        super(ioService);
        super.delimiter = "---------------------------------------------------------------";
        super.formatString = "|%5s| %-30s| %-15s| %-5s|%n";
        super.formatArgs = new Object[]{" ID ", "          ИМЯ", "    ФАМИЛИЯ ", "И.О."};
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
