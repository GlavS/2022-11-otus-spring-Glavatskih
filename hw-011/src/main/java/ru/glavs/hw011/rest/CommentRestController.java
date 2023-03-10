//package ru.glavs.hw008.rest;
//
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import ru.glavs.hw008.domain.Book;
//import ru.glavs.hw008.domain.Comment;
//import ru.glavs.hw008.rest.dto.CommentDto;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//@RestController
//@AllArgsConstructor
//public class CommentRestController {
//
//
//    @GetMapping("/api/comments")
//    public Comment getCommentById(@RequestParam String commentId) {
//        return commentCRUDService.findById(commentId);
//    }
//
//    @PatchMapping("/api/comments")
//    public Comment updateComment(@RequestBody CommentDto dto) {
//        Date commentDate = stringToDate(dto.getDate());
//        Book commentedBook = bookCRUDService.getById(dto.getCommentedBookId());
//        return commentCRUDService.save(new Comment(
//                dto.getId(),
//                dto.getText(),
//                dto.getAuthorNick(),
//                commentDate,
//                commentedBook
//        ));
//    }
//
//    @PostMapping("/api/comments")
//    public Comment createComment(@RequestBody CommentDto dto) {
//        Date commentDate = stringToDate(dto.getDate());
//        Book commentedBook = bookCRUDService.getById(dto.getCommentedBookId());
//        return commentCRUDService.save(new Comment(
//                dto.getText(),
//                dto.getAuthorNick(),
//                commentDate,
//                commentedBook
//        ));
//    }
//
//    @DeleteMapping("/api/comments")
//    public void deleteComment(@RequestParam String id){
//        commentCRUDService.deleteById(id);
//    }
//
//    private Date stringToDate(String dateString) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date parsedDate;
//        try {
//            parsedDate = format.parse(dateString);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        return parsedDate;
//    }
//}
