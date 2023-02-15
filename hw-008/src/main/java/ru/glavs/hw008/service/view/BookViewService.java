package ru.glavs.hw008.service.view;

import org.springframework.stereotype.Service;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.domain.Genre;
import ru.glavs.hw008.domain.projections.BookWithComments;
import ru.glavs.hw008.io.IOService;

import java.util.List;

@Service
public class BookViewService extends AbstractViewService<BookWithComments> implements ViewService<BookWithComments> {
    private static final String ITEM_FORMAT_STRING = "| %-20s| %-40s| %-25s| %-30s|%n";
    private static final int STRIPPED_COMMENT_LENGTH = 25;
    private static final int STRIPPED_AUTHOR_LENGTH = 20;
    private static final int STRIPPED_GENRE_LENGTH = 25;

    public BookViewService(IOService ioService) {
        super(ioService);
        super.delimiter = "----------------------------------------------------------------------------------------------------------------------------";
        super.formatString = "| %-20s| %-40s| %-25s| %-30s|%n";
        super.formatArgs = new Object[]{"     АВТОР", "          ЗАГЛАВИЕ", "   ЖАНР", "   КОММЕНТАРИЙ "};
    }

    @Override
    protected void displayItem(BookWithComments book) {
        String comments = formatComments(book.getComments());
        ioService.printf(ITEM_FORMAT_STRING,
                formatAuthors(book.getAuthors()),
                book.getTitle(),
                formatGenres(book.getGenres()),
                comments
        );
    }

    private String formatComments(List<Comment> commentList) {
        StringBuilder comments = new StringBuilder();

        for (Comment c : commentList) {
            if (c != null) {
                comments.append(c.getText())
                        .append(" ");
            }
        }
        if (comments.length() > 0 && comments.length() <= STRIPPED_COMMENT_LENGTH) {
            int commentLength = comments.length();
            comments.append(" ".repeat(STRIPPED_COMMENT_LENGTH - commentLength));
        } else if (comments.length() > STRIPPED_COMMENT_LENGTH) {
            comments.setLength(STRIPPED_COMMENT_LENGTH);
            comments.append("...");
        }
        return comments.toString();
    }

    private String formatAuthors(List<Author> authorList) {
        StringBuilder sb = new StringBuilder();
        for (Author a : authorList) {
            sb.append(a.getInitials()).append(" ")
                    .append(a.getSurname()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        String result = sb.toString();
        if (result.length() > STRIPPED_AUTHOR_LENGTH) {
            return result.substring(0, STRIPPED_AUTHOR_LENGTH - 1);
        } else {
            return result;
        }
    }

    private String formatGenres(List<Genre> genreList) {
        StringBuilder sb = new StringBuilder();
        for (Genre g : genreList) {
            sb.append(g.getName()).append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        String result = sb.toString();
        if (result.length() > STRIPPED_GENRE_LENGTH) {
            return result.substring(0, STRIPPED_GENRE_LENGTH - 1);
        } else {
            return result;
        }
    }

}
