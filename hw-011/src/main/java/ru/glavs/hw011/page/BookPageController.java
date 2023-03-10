//package ru.glavs.hw008.page;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.reactive.result.view.Rendering;
//import reactor.core.publisher.Mono;
//
//@Controller
//public class BookPageController {
//
//
//    @GetMapping("/")
//    public Mono<Rendering> listBooksPage() {
//        return Mono.just(Rendering.view("list").build());
//    }
//
////    @GetMapping("/book-edit")
////    public String bookEditPage(@RequestParam String id, Model model){
////        model.addAttribute("bookId", id);
////        return "edit/book-edit";
////    }
////    @GetMapping("/book-show")
////    public String bookShowPage(@RequestParam String id, Model model){
////        model.addAttribute("bookId", id);
////        return "show";
////    }
////    @GetMapping("/book-create")
////    public String bookCreatePage(){
////        return "create/book-create";
////    }
//}
