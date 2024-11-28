package com.wisescatalog.api.service;


import com.wisescatalog.api.dto.Books;
import com.wisescatalog.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.wisescatalog.api.repository.BooksRepository;

import java.util.List;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Cacheable(value = "genre")
    public List<Books> getBooksByGenre(String genre) {
        return booksRepository.findByGenreIgnoreCase(genre);
    }

    @Cacheable(value = "author")
    public List<Books> getBooksByAuthor(String author) {
        return booksRepository.findByAuthorIgnoreCase(author);
    }

    public Page<Books> getAllBooks(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    @Cacheable(value = "books")
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    @Cacheable(value = "books", key = "#id")
    public Books getBookById(Long id) {
        return booksRepository.findById(id.toString()).orElseThrow(ResourceNotFoundException::new);
    }

    public void saveAllBooks(List<Books> booksList) {
        booksRepository.saveAll(booksList);
    }
}
