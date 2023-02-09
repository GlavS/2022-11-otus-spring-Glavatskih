package ru.glavs.hw008;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class Hw008Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw008Application.class);
        //ConfigurableApplicationContext context =
//        BookRepository bookRepository = context.getBean(BookRepository.class);
//        CommentRepository commentRepository = context.getBean(CommentRepository.class);
//        AbstractViewService<BookWithComments> bookViewService = context.getBean(BookViewService.class);
//
//        List<Book> bookList = bookRepository.findAll();
//
////        Book bookToComment = bookList.get(1);
////        String commentText = "Это самое лучшее, что я читал в своей жизни об основаниях.";
////        Comment comment = new Comment(commentText, "GlavS", new Date(), bookToComment);
////        commentRepository.save(comment);
//
//        List<Comment> commentList = commentRepository.findAll();
//        List<BookWithComments> commentedBooks = bookRepository.findAllWithComments();
//        bookViewService.printList(commentedBooks);
//        List<Book> bookVoskr = bookRepository.findByTitleContaining("Воскр");
    }
}
