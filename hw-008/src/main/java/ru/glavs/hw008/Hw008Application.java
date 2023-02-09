package ru.glavs.hw008;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.glavs.hw008.domain.projections.BookComments;
import ru.glavs.hw008.repository.BookRepository;
import ru.glavs.hw008.repository.CommentRepository;
import ru.glavs.hw008.domain.Book;
import ru.glavs.hw008.domain.Comment;
import ru.glavs.hw008.service.view.AbstractViewService;
import ru.glavs.hw008.service.view.BookViewService;

import java.util.Date;
import java.util.List;

//@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class Hw008Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Hw008Application.class);
        BookRepository bookRepository = context.getBean(BookRepository.class);
        CommentRepository commentRepository = context.getBean(CommentRepository.class);
        AbstractViewService<BookComments> bookViewService = context.getBean(BookViewService.class);

        List<Book> bookList = bookRepository.findAll();

//        Book bookToComment = bookList.get(1);
//        String commentText = "Это самое лучшее, что я читал в своей жизни об основаниях.";
//        Comment comment = new Comment(commentText, "GlavS", new Date(), bookToComment);
//        commentRepository.save(comment);

        List<Comment> commentList = commentRepository.findAll();
        List<BookComments> commentedBooks = bookRepository.findAllWithComments();
        bookViewService.printList(commentedBooks);
        List<Book> bookVoskr = bookRepository.findByTitleContaining("Воскр");
    }
}
