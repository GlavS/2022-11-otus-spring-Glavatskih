package ru.glavs.hw007.service.view;

import org.springframework.stereotype.Service;
import ru.glavs.hw007.domain.Comment;
import ru.glavs.hw007.io.IOService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CommentViewService extends AbstractViewService<Comment> implements ViewService<Comment> {

    private static final String ITEM_FORMAT_STRING = "     | %-4d| %-50s| %-20s| %-12s|%n";
    private static final String LIST_ITEM_FORMAT_STRING = "     | %-4s| %-50s| %-20s| %-12s|%n";
    private static final int COMMENT_COLUMN_WIDTH = 50;

    public CommentViewService(IOService ioService) {
        super(ioService);
        super.delimiter = "     -----------------------------------------------------------------------------------------------";
        super.formatString = "     | %-4s| %-50s| %-20s| %-12s|%n";
        super.formatArgs = new Object[]{" ID ", "        КОММЕНТАРИЙ", "    НИК ", "   ДАТА"};
    }

    @Override
    protected void displayItem(Comment comment) {
        SimpleDateFormat commentDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        List<String> formattedCommentText = formatCommentText(comment.getText());

        if (formattedCommentText.size() == 1) {
            ioService.printf(ITEM_FORMAT_STRING,
                    comment.getId(),
                    comment.getText(),
                    comment.getAuthorNick(),
                    commentDateFormat.format(comment.getDate()));
        } else {
            ioService.printf(ITEM_FORMAT_STRING,
                    comment.getId(),
                    formattedCommentText.get(0),
                    comment.getAuthorNick(),
                    commentDateFormat.format(comment.getDate()));
            for (int i = 1; i < formattedCommentText.size(); i++) {
                ioService.printf(LIST_ITEM_FORMAT_STRING,
                        "", formattedCommentText.get(i), "", "");
            }
        }
    }

    private List<String> formatCommentText(String comment) {

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
        if (sb.length() > 0) {
            result.add(sb.toString());
        }
        if(result.size() == 1){
            StringBuilder lineSb = new StringBuilder(result.get(0));
            lineSb.append(" ".repeat(Math.max(0, COMMENT_COLUMN_WIDTH - lineSb.length())));
            result.set(0, lineSb.toString());
        }
        return result;
    }
}


