package ru.glavs.hw014.service.view;

import org.springframework.stereotype.Service;
import ru.glavs.hw014.domain.Genre;
import ru.glavs.hw014.io.IOService;

@Service
public class GenreViewService extends AbstractViewService<Genre> implements ViewService<Genre> {
    private static final String ITEM_FORMAT_STRING = "|%-5d| %-30s|%n";

    public GenreViewService(IOService ioService) {
        super(ioService);
        super.delimiter = "---------------------------------------";
        super.formatString = "|%-5s| %-30s|%n";
        super.formatArgs = new Object[]{" ID ", "           ЖАНР   "};
    }

    @Override
    protected void displayItem(Genre genre) {
        ioService.printf(ITEM_FORMAT_STRING,
                genre.getId(),
                genre.getGenre()
        );
    }
}
