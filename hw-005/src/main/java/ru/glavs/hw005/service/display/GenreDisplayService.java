package ru.glavs.hw005.service.display;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Genre;
import ru.glavs.hw005.io.IOService;

@Service
public class GenreDisplayService extends AbstractTableFormat<Genre> implements DisplayService<Genre> {
    private static final String ITEM_FORMAT_STRING = "|%-5d| %-30s|%n";

    public GenreDisplayService(IOService ioService) {
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
