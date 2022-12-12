package ru.otus.glavs.l10n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.glavs.properties.Application;
@Component
public class LocalizedExamAnalyzerMessagesStorage extends LocalizedMessagesStorage {

    public LocalizedExamAnalyzerMessagesStorage(Application props, MessageSource locMessage) {
        super(props, locMessage);
    }

    public String getPrintResultsCaption() {
        return locMessage.getMessage("examanalyzer.printresults.caption", null, locale);
    }

    public String getPrintResultsExamPassed() {
        return locMessage.getMessage("examanalyzer.printresults.exampassed", null, locale);
    }

    public String getPrintResultsExamNotPassed() {
        return locMessage.getMessage("examanalyzer.printresults.examnotpassed", null, locale);
    }

    public String getPrintResultsResults() {
        return locMessage.getMessage("examanalyzer.printresults.results", null, locale);
    }

    public String getPrintMistakesCaption() {
        return locMessage.getMessage("examanalyzer.printmistakess.caption", null, locale);
    }

    public String getPrintMistakesResult() {
        return locMessage.getMessage("examanalyzer.printmistakess.result", null, locale);
    }
}
