package com.wisescatalog.api.controller;

import com.wisescatalog.api.dto.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.wisescatalog.api.service.BooksService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService bookService;

    @Autowired
    public BooksController(BooksService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getBooks(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size){
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(bookService.getAllBooks(pageable));
        } else {
            return ResponseEntity.ok(bookService.getAllBooks());
        }
    }

    @GetMapping(params = "id")
    public ResponseEntity<Books> getBookById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(this.bookService.getBookById(id));
    }

    @GetMapping(path = "/genre")
    public ResponseEntity<List<Books>> getBooksByGenre(@RequestParam(name = "genre") String genre) {
        return ResponseEntity.ok(this.bookService.getBooksByGenre(genre));
    }

    @GetMapping(path = "/author")
    public ResponseEntity<List<Books>> getBooksByAuthor(@RequestParam(name = "author") String author) {
        return ResponseEntity.ok(this.bookService.getBooksByAuthor(author));
    }
}
