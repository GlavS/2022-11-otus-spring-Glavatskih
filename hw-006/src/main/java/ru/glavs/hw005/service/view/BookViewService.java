package ru.glavs.hw005.service.view;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Book;
import ru.glavs.hw005.domain.Comment;
import ru.glavs.hw005.io.IOService;

import java.util.List;

@Service
public class BookViewService extends AbstractViewService<Book> implements ViewService<Book> {
    private static final String ITEM_FORMAT_STRING = "|%-5d| %-5s%-15s| %-40s| %-15s| %-30s|%n";
    private static final int STRIPPED_COMMENT_LENGTH = 25;

    public BookViewService(IOService ioService) {
        super(ioService);
        super.delimiter = "------------------------------------------------------------------------------------------------------------------------";
        super.formatString = "|%-5s| %-20s| %-40s| %-15s| %-30s|%n";
        super.formatArgs = new Object[]{" ID ", "     АВТОР", "          ЗАГЛАВИЕ", "   ЖАНР", "   КОММЕНТАРИЙ "};
    }

    @Override
    protected void displayItem(Book book) {
        String comments = formatComments(book.getComments());
        ioService.printf(ITEM_FORMAT_STRING,
                book.getId(),
                book.getAuthor().getInitials(),
                book.getAuthor().getSurname(),
                book.getTitle(),
                book.getGenre().getGenre(),
                comments);
    }
    private String formatComments(List<Comment> commentList){
        StringBuilder comments = new StringBuilder();

        for (Comment c : commentList) {
            if (c != null) {
                comments.append(c.getText())
                        .append(" ");
            }
        }
        if (comments.length() > 0 && comments.length() <= STRIPPED_COMMENT_LENGTH) {
            int commentLength =  comments.length();
            comments.append(" ".repeat(STRIPPED_COMMENT_LENGTH - commentLength));
        } else if (comments.length() > STRIPPED_COMMENT_LENGTH) {
            comments.setLength(STRIPPED_COMMENT_LENGTH);
            comments.append("...");
        }
        return comments.toString();
    }
}
