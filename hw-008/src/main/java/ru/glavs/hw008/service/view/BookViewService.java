package ru.glavs.hw008.service.view;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.projections.BookComments;
import ru.glavs.hw008.io.IOService;

import java.util.List;

@Service
public class BookViewService extends AbstractViewService<BookComments> implements ViewService<BookComments> {
    private static final String ITEM_FORMAT_STRING = "| %-5s%-15s| %-40s| %-25s| %-30s|%n";
    private static final int STRIPPED_COMMENT_LENGTH = 25;

    public BookViewService(IOService ioService) {
        super(ioService);
        super.delimiter = "----------------------------------------------------------------------------------------------------------------------------";
        super.formatString = "| %-20s| %-40s| %-25s| %-30s|%n";
        super.formatArgs = new Object[]{"     АВТОР", "          ЗАГЛАВИЕ", "   ЖАНР", "   КОММЕНТАРИЙ "};
    }

    @Override
    protected void displayItem(BookComments book) {
        String comments = formatComments(book.getComments());
        ioService.printf(ITEM_FORMAT_STRING,
                book.getAuthors().get(0).getInitials(),
                book.getAuthors().get(0).getSurname(),
                book.getTitle(),
                book.getGenres().get(0).getName(),
                comments
        );
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
