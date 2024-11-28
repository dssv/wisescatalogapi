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
    public List<Books> getBooksByGenre(String genre) throws ResourceNotFoundException {
        List<Books> booksList = booksRepository.findByGenreIgnoreCase(genre);
        if (booksList.isEmpty()){
            throw new ResourceNotFoundException("Books not found.");
        } else {
            return booksList;
        }
    }

    @Cacheable(value = "author")
    public List<Books> getBooksByAuthor(String author) throws ResourceNotFoundException {
        List<Books> booksByAuthor = booksRepository.findByAuthorIgnoreCase(author);
        if (booksByAuthor.isEmpty()) {
            throw new ResourceNotFoundException("Books not found");
        } else {
            return booksByAuthor;
        }
    }

    public Page<Books> getAllBooks(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    @Cacheable(value = "books")
    public List<Books> getAllBooks() throws ResourceNotFoundException {
        List<Books> booksList =  booksRepository.findAll();
        if (booksList.isEmpty()) {
            throw new ResourceNotFoundException("Books not found");
        } else {
            return booksList;
        }
    }

    @Cacheable(value = "books", key = "#id")
    public Books getBookById(Long id) throws ResourceNotFoundException {
        Optional<Books> book = booksRepository.findById(id.toString());
        if (book.isEmpty()) {
            throw new ResourceNotFoundException("Books not found");
        } else {
            return book.get();
        }
    }

    public void saveAllBooks(List<Books> booksList) {
        booksRepository.saveAll(booksList);
    }
}
