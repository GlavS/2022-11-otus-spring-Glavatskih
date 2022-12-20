package ru.otus.glavs.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.glavs.service.ExamService;

@ShellComponent
public class ExamCommands implements Exam {
    private final ExamService examService;

    public ExamCommands(ExamService examService) {
        this.examService = examService;
    }

    @Override
    @ShellMethod("Start examination service")
    public void exam() {
        this.examService.examine();
    }

    @Override
    public void register() {

    }
}
