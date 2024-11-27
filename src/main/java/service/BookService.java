package service;


import entity.Books;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.BooksRepository;

import java.util.List;

@Service
public class BookService {

    private BooksRepository booksRepository;

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
    public Books getBookById(String id) {
        return booksRepository.findById(id).orElse(null);
    }
}
