package ru.glavs.hw014.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Date;

@ShellComponent
@RequiredArgsConstructor
public class BatchJobShellService {

    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final Job booksToMongoJob;

    @ShellMethod(value = "Copy books to MongoDB using JobLauncher", key = "books-copy-jl")
    public void launchBooksCopyToMongoJobJL() throws Exception {
        JobExecution execution = jobLauncher.run(booksToMongoJob, new JobParametersBuilder()
                .addDate("date", new Date()).toJobParameters());
        System.out.println(execution);
    }

    @ShellMethod(value = "Copy books to MongoDB using JobOperator", key = "books-copy-jo")
    public void launchBooksCopyToMongoJobJO() throws Exception {
        Long execID = jobOperator.start("saveBooksToMongoDBJob", "date" + new Date());
        System.out.println(jobOperator.getSummary(execID));
    }
}
