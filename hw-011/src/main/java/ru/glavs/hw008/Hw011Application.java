package ru.glavs.hw008;

//import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.glavs.hw008.domain.Author;
import ru.glavs.hw008.repository.AuthorRepository;
import ru.glavs.hw008.repository.BookRepository;
import ru.glavs.hw008.repository.CommentRepository;
import ru.glavs.hw008.repository.GenreRepository;

//@EnableMongock
@EnableWebFlux
@EnableReactiveMongoRepositories
@SpringBootApplication
public class Hw011Application {

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext context = SpringApplication.run(Hw011Application.class);
        System.out.println("Main page: http://localhost:8080");

        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        GenreRepository genreRepository = context.getBean(GenreRepository.class);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        CommentRepository commentRepository = context.getBean(CommentRepository.class);
//        Author author = new Author("Alex", "Pushkin", "A.S.");
//        Mono<Author> monoAuthor = repository.save(author);
        Thread.sleep(3000);
        authorRepository.findAll().subscribe(System.out::println);
        genreRepository.findAll().subscribe(System.out::println);
        bookRepository.findAll().subscribe(System.out::println);
        commentRepository.findAll().subscribe(System.out::println);

    }
}
