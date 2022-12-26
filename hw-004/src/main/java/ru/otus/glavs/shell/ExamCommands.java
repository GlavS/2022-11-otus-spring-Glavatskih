package ru.otus.glavs.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.Availability;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.glavs.l10n.LocaleNotSupportedException;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.ExamService;
import ru.otus.glavs.service.ioservice.IOService;
import ru.otus.glavs.service.ioservice.LocalizedIOService;

import java.util.Locale;

@ShellComponent
public class ExamCommands implements Exam, PromptProvider {
    private final ExamService examService;
    private final IOService ioService;
    private final LocalizedIOService localizedIOService;
    private final LocalizedMessages messagesProvider;
    private boolean registered;
    private String licenseNumber;


    public ExamCommands(ExamService examService,
                        IOService ioService,
                        LocalizedIOService localizedIOService,
                        LocalizedMessages provider) {
        this.examService = examService;
        this.ioService = ioService;
        this.localizedIOService = localizedIOService;
        this.messagesProvider = provider;
        this.registered = false;
    }

    @Override
    @ShellMethod("Start examination service")
    @ShellMethodAvailability("registrationCheck")
    public void exam() {
        this.examService.examine();
    }

    @Override
    @ShellMethod("Register student for exam")
    public void register() {
        String license;
        license = localizedIOService.readStringWithPrompt("examcommands.register.prompt");
        while (!license.matches("^\\d{7}$")) {
            license = localizedIOService.readStringWithPrompt("examcommands.register.incorrect");
        }
        localizedIOService.writeMessage("examcommands.register.accepted");
        ioService.writeln(" " + license);
        localizedIOService.writeMessage("examcommands.register.proceed");
        this.licenseNumber = license;
        this.registered = true;
    }

    @ShellMethod("Set language. Usage: language language_code [country_code]")
    public void language(@ShellOption(help = "Language code, e.g. \"en\" or \"ru\" ") String language,
                         @ShellOption(help = "Country code, e.g. \"RU\". Don't use for English!", defaultValue = "") String country) {
        try {
            this.messagesProvider.changeDefaultLocale(new Locale(language, country));
        } catch (LocaleNotSupportedException e) {
            throw new RuntimeException("Error during locale change: " + e.getMessage(), e);
        }
    }

    private Availability registrationCheck() {
        return registered ?
                Availability.available() :
                Availability.unavailable(messagesProvider.getTextMessage("examcommands.availability.check"));
    }

    @Override
    public AttributedString getPrompt() {
        if (registered) {
            String prompt = "exam-license-" + licenseNumber + ":>";
            return new AttributedString(prompt, AttributedStyle.BOLD.foreground(AttributedStyle.GREEN));
        } else {
            return new AttributedString("exam-unregistered:>", AttributedStyle.BOLD.foreground(AttributedStyle.RED));
        }
    }
}
