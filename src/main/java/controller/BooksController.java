package controller;

import dto.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BooksService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService bookService;

    @Autowired
    public BooksController(BooksService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<Books>> getBooks(Pageable pageable){
        return ResponseEntity.ok(this.bookService.getAllBooks(pageable));
    }

    @GetMapping
    public ResponseEntity<Books> getBookById(@RequestParam(name = "id") Long id) {
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
