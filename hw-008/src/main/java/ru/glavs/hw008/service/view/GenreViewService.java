package ru.glavs.hw008.service.view;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.io.IOService;

@Service
public class GenreViewService extends AbstractViewService<Genre> implements ViewService<Genre> {
    private static final String ITEM_FORMAT_STRING = "| %-30s|%n";

    public GenreViewService(IOService ioService) {
        super(ioService);
        super.delimiter = "---------------------------------------";
        super.formatString = "| %-30s|%n";
        super.formatArgs = new Object[]{"           ЖАНР   "};
    }

    @Override
    protected void displayItem(Genre genre) {
        ioService.printf(ITEM_FORMAT_STRING,
                genre.getName()
        );
    }
}
