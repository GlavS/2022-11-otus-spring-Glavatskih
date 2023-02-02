package ru.glavs.hw007.service.view;

import org.springframework.stereotype.Service;
import ru.glavs.hw007.domain.Author;
import ru.glavs.hw007.io.IOService;

@Service
public class AuthorViewService extends AbstractViewService<Author> implements ViewService<Author> {

    private static final String ITEM_FORMAT_STRING = "|%-5d| %-30s| %-15s| %-5s|%n";

    public AuthorViewService(IOService ioService) {
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
