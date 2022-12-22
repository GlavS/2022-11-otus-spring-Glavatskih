package ru.otus.glavs.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.Availability;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.glavs.l10n.LocalizedMessages;
import ru.otus.glavs.service.ExamService;
import ru.otus.glavs.service.ioservice.IOService;

import java.util.Scanner;

@ShellComponent
public class ExamCommands implements Exam, PromptProvider {
    private final ExamService examService;
    private final IOService ch;
    private final LocalizedMessages storage;
    private boolean registered;
    private String licenseNumber;

    public ExamCommands(ExamService examService, IOService ch, LocalizedMessages storage) {
        this.examService = examService;
        this.ch = ch;
        this.storage = storage;
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
        Scanner scanner = new Scanner(System.in);
        ch.writeln(storage.getTextMessage("examcommands.register.prompt"));
        String license = scanner.nextLine();
        while (!license.matches("^\\d{7}$")) {
            ch.writeln(storage.getTextMessage("examcommands.register.incorrect"));
            license = scanner.nextLine();
        }
        ch.writeln(storage.getTextMessage("examcommands.register.accepted") + license);
        ch.writeln(storage.getTextMessage("examcommands.register.proceed"));
        this.licenseNumber = license;
        this.registered = true;
    }

    private Availability registrationCheck() {
        return registered ?
                Availability.available() :
                Availability.unavailable(storage.getTextMessage("examcommands.availability.check"));
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
