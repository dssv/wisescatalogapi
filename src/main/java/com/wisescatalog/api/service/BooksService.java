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
import java.util.Optional;

@Service
public class BooksService {

    private final BooksRepository booksRepository;
    //private final RecentlyViewedService recentlyViewedService;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
                        //RecentlyViewedService recentlyViewedService) {
        this.booksRepository = booksRepository;
        //this.recentlyViewedService = recentlyViewedService;
    }

    @Cacheable(value = "genre")
    public List<Books> getBooksByGenre(String genre) {
        return Optional.ofNullable(booksRepository.findByGenreIgnoreCase(genre))
                .filter(list -> !list.isEmpty())
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Cacheable(value = "author")
    public List<Books> getBooksByAuthor(String author) {
        List<Books> booksByAuthor = booksRepository.findByAuthorIgnoreCase(author);
        if (booksByAuthor.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return booksByAuthor;
        }
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
        Books book = booksRepository.findById(id.toString()).orElseThrow(ResourceNotFoundException::new);
        //recentlyViewedService.registerView(book);
        return book;
    }

    public void saveAllBooks(List<Books> booksList) {
        booksRepository.saveAll(booksList);
    }
}
