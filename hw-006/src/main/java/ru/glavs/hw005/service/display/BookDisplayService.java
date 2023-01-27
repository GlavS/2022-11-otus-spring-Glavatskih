package ru.glavs.hw005.service.display;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;
import ru.glavs.hw005.io.IOService;

import java.util.stream.Collectors;

@Service
public class BookDisplayService extends AbstractDisplayService<Book> implements DisplayService<Book> {
    private static final String ITEM_FORMAT_STRING = "|%-5d| %-5s%-15s| %-40s| %-15s| %-30s|%n";

    public BookDisplayService(IOService ioService) {
        super(ioService);
        super.delimiter = "------------------------------------------------------------------------------------------------------------------------";
        super.formatString = "|%-5s| %-20s| %-40s| %-15s| %-30s|%n";
        super.formatArgs = new Object[]{" ID ", "     АВТОР", "          ЗАГЛАВИЕ", "   ЖАНР", "   КОММЕНТАРИЙ "};
    }

    @Override
    protected void displayItem(Book book) {
        StringBuilder comments = new StringBuilder();//TODO:Better refactor

        for (Comment c : book.getComments()) {
            if(c != null) {
                comments.append(c.getText())
                        .append(" ");
            }
        }
        if(!comments.toString().equals("")) {
            comments.setLength(25);
            comments.append("...");
        }
        ioService.printf(ITEM_FORMAT_STRING,
                book.getId(),
                book.getAuthor().getInitials(),
                book.getAuthor().getSurname(),
                book.getTitle(),
                book.getGenre().getGenre(),
                comments.toString());
    }
}
