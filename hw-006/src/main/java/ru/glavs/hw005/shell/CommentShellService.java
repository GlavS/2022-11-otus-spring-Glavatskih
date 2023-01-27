package ru.glavs.hw005.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.glavs.hw005.io.IOService;

@ShellComponent
@ShellCommandGroup("02. Comment CRUD")
@AllArgsConstructor
public class CommentShellService {

    private final IOService ioService;
    @ShellMethod("Add comment to book.")
    void commentAdd(){
        ioService.println("Should add comment"); //TODO:
    }
    @ShellMethod("Delete existing book comment.")
    void commentDelete(){
        ioService.println("Should delete comment"); //TODO:
    }

}
