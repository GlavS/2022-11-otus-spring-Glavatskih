package ru.glavs.hw014.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.transaction.PlatformTransactionManager;
import ru.glavs.hw014.batch.mongo.MongoBook;
import ru.glavs.hw014.dao.BookDao;
import ru.glavs.hw014.domain.Book;

import java.util.List;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {
    private final JobRepository jobRepository;
    private final JobRegistry jobRegistry;
    private final PlatformTransactionManager manager;
    private final MongoOperations mongoTemplate;
    private final BookDao bookDao;
    private final BookProcessor processor;

    //private final ConsoleItemWriter<MongoBook> consoleWriter; // For testing purposes

    public static final String JOB_NAME = "saveBooksToMongoDBJob";

    @Bean
    public RepositoryItemReader<Book> reader() {
        return new RepositoryItemReaderBuilder<Book>()
                .name("bookReader")
                .repository(bookDao)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public MongoItemWriter<MongoBook> writer() {
        return new MongoItemWriterBuilder<MongoBook>()
                .collection("mongoBooks")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Step bookSaveToMongoStep() {
        return new StepBuilder("saveBooksToMongoDBStep")
                .<Book, MongoBook>chunk(10)
                .reader(reader())
                .processor(processor)
                .writer(writer())
                .listener(
                        new ItemWriteListener<MongoBook>() {
                            @Override
                            public void beforeWrite(List<? extends MongoBook> items) {

                            }

                            @Override
                            public void afterWrite(List<? extends MongoBook> items) {
                                for (MongoBook book : items) {
                                    System.out.println("Written: " + book);
                                }
                            }

                            @Override
                            public void onWriteError(Exception exception, List<? extends MongoBook> items) {

                            }
                        }
                )
                .transactionManager(manager)
                .repository(jobRepository)
                .build();
    }

    @Bean
    public Job booksToMongoJob() {
        return new JobBuilder(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(bookSaveToMongoStep())
                .repository(jobRepository)
                .build();
    }

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBPP() {
        JobRegistryBeanPostProcessor processor = new JobRegistryBeanPostProcessor();
        processor.setJobRegistry(jobRegistry);
        return processor;
    }
}
