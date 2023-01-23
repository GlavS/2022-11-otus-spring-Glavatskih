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
    private static final String LIST_ITEM_FORMAT_STRING = "| %-4s| %-50s| %-15s| %-12s|%n";//TODO:rename
    private static final int COMMENT_COLUMN_WIDTH = 50;

    public CommentDisplayService(IOService ioService) {
        super(ioService);
        super.delimiter = "------------------------------------------------------------------------------------------";
        super.formatString = "|%5s| %-50s| %-15s| %-12s|%n";
        super.formatArgs = new Object[]{" ID ", "        КОММЕНТАРИЙ", "    НИК ", "   ДАТА"};
    }

    @Override
    protected void displayItem(Comment comment) {//TODO:refactor, everything shift to the right
        SimpleDateFormat commentDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        List<String> commentText = textFormatter(comment.getText());

        if(commentText.size() == 1) {
            ioService.printf(ITEM_FORMAT_STRING,
                    comment.getId(),
                    comment.getText(),
                    comment.getAuthorNick(),
                    commentDateFormat.format(comment.getDate()));
        } else {
            ioService.printf(ITEM_FORMAT_STRING,
                    comment.getId(),
                    commentText.get(0),
                    comment.getAuthorNick(),
                    commentDateFormat.format(comment.getDate()));
            for (int i = 1; i < commentText.size(); i++) {
                ioService.printf(LIST_ITEM_FORMAT_STRING,
                        "", commentText.get(i), "", "");
            }
        }
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
                currentWidth = word.length() + 1;
            }
            sb.append(word).append(" ");

        }
        if(sb.length() > 0) {
            result.add(sb.toString());
        }
        return result;
    }
}


