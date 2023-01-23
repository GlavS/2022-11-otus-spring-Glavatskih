package ru.glavs.hw005.service.display;

import org.springframework.stereotype.Service;
import ru.glavs.hw005.domain.Comment;
import ru.glavs.hw005.io.IOService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CommentDisplayService extends AbstractDisplayService<Comment> implements DisplayService<Comment> {

    private static final String ITEM_FORMAT_STRING = "| %-4d| %-50s| %-15s| %-12s|%n";
    private static final int COMMENT_COLUMN_WIDTH = 50;

    public CommentDisplayService(IOService ioService) {
        super(ioService);
        super.delimiter = "------------------------------------------------------------------------------------------";
        super.formatString = "|%5s| %-50s| %-15s| %-12s|%n";
        super.formatArgs = new Object[]{" ID ", "        КОММЕНТАРИЙ", "    НИК ", "   ДАТА"};
    }

    @Override
    protected void displayItem(Comment comment) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        ioService.printf(ITEM_FORMAT_STRING,
                comment.getId(),
                comment.getText(),
                comment.getAuthorNick(),
                format.format(comment.getDate()));
    }
    private List<String> textFormatter(String comment){

        List<String> splittedComment = List.of(comment.split(" "));
        List<String> result = new ArrayList<>();
        int currentWidth = 0;
        StringBuilder sb = new StringBuilder();
        for (String s : splittedComment) {
            String word = s.strip();
            currentWidth += (word.length() + 1);
            if (currentWidth > COMMENT_COLUMN_WIDTH) {
                result.add(sb.toString());
                sb.setLength(0);
                currentWidth = 0;
            }
            sb.append(word).append(" ");
        }
        return result;
    }
}


