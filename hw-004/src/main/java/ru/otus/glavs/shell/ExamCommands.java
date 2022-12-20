package ru.otus.glavs.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.glavs.service.ExamService;

import java.util.Scanner;

@ShellComponent
public class ExamCommands implements Exam {
    private final ExamService examService;
    private boolean registered;

    public ExamCommands(ExamService examService) {
        this.examService = examService;
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
        System.out.println("Please enter your exam license number (seven digits): ");
        String license = scanner.nextLine();
        while (!license.matches("^\\d{7}$")) {
            System.out.println("Incorrect license, please retry (seven digits): ");
            license = scanner.nextLine();
        }
        System.out.println("Registration accepted. Your license is: " + license);
        System.out.println("You can proceed for examination. Please use <exam> command.");
        this.registered = true;
    }

    public Availability registrationCheck() {
        return registered ?
                Availability.available() :
                Availability.unavailable("you are not registered." +
                        System.lineSeparator() +
                        "\t*** Use <register> command for registration ***");
    }
}
