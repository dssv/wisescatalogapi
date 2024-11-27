package repository;

import entity.Books;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksRepository extends MongoRepository<Books, String> {

    // Find books by genre
    List<Books> findByGenreIgnoreCase(String genre);

    // Fund books by author
    List<Books> findByAuthorIgnoreCase(String author);
}
