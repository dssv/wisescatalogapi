package service;


import dto.Books;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.BooksRepository;

import java.util.List;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Cacheable(value = "books", key = "#genre")
    public List<Books> getBooksByGenre(String genre) {
        return booksRepository.findByGenreIgnoreCase(genre);
    }

    @Cacheable(value = "books", key = "#author")
    public List<Books> getBooksByAuthor(String author) {
        return booksRepository.findByAuthorIgnoreCase(author);
    }

    @Cacheable(value = "books", key = "'page:' + #pageable.pageNumber + ':size:' + #pageable.pageSize")
    public Page<Books> getAllBooks(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    @Cacheable(value = "books", key = "#id")
    public Books getBookById(Long id) {
        return booksRepository.findById(id.toString()).orElseThrow(ResourceNotFoundException::new);
    }
}
