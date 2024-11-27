package repository;

import dto.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksRepository extends JpaRepository<Books, String> {

    // Find books by genre
    List<Books> findByGenreIgnoreCase(String genre);

    // Fund books by author
    List<Books> findByAuthorIgnoreCase(String author);
}
