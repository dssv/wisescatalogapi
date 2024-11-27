package controller;

import entity.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<Books>> getBooks(Pageable pageable){
        return ResponseEntity.ok(this.bookService.getAllBooks(pageable));
    }

    @GetMapping
    public ResponseEntity<Books> getBookById(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok(this.bookService.getBookById(id));
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Books>> getBooksByGenre(@RequestParam(name = "genre") String genre) {
        return ResponseEntity.ok(this.bookService.getBooksByGenre(genre));
    }

    @GetMapping("/author")
    public ResponseEntity<List<Books>> getBooksByAuthor(@RequestParam(name = "author") String author) {
        return ResponseEntity.ok(this.bookService.getBooksByAuthor(author));
    }
}
