package ru.glavs.hw014.batch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
@DisplayName("Модуль пакетной обработки должен")
class BatchJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncher;

    @Test
    @DisplayName("Успешно завершить пакетное задание")
    public void jobWriteBooksToMongoShouldFinishSuccessfully() throws Exception {

        JobExecution execution = jobLauncher.launchJob();
        assertThat(execution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }

}