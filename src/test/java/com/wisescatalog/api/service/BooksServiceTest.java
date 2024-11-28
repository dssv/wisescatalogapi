package com.wisescatalog.api.service;

import com.wisescatalog.api.dto.Books;
import com.wisescatalog.api.exception.ResourceNotFoundException;
import com.wisescatalog.api.repository.BooksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BooksServiceTest {

    @InjectMocks
    private BooksService booksService;

    @Mock
    private BooksRepository booksRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBooksByGenre_ReturnsBooksList_WhenBooksExist() throws Exception {
        // Given
        String genre = "Fantasy";
        List<Books> booksList = Arrays.asList(new Books(), new Books());

        // When
        when(booksRepository.findByGenreIgnoreCase(genre)).thenReturn(booksList);
        List<Books> result = booksService.getBooksByGenre(genre);

        // Assert
        assertEquals(2, result.size());
        verify(booksRepository, times(1)).findByGenreIgnoreCase(genre);
    }

    @Test
    public void testGetBooksByGenre_ThrowsResourceNotFoundException_WhenNoBooksFound() {
        // Given
        String genre = "Fantasy";

        // When
        when(booksRepository.findByGenreIgnoreCase(genre)).thenReturn(Collections.emptyList());

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> booksService.getBooksByGenre(genre));
        verify(booksRepository, times(1)).findByGenreIgnoreCase(genre);
    }

    @Test
    public void testGetBooksByAuthor_ReturnsBooksList_WhenBooksExist() throws Exception {
        // Given
        String author = "J.K. Rowling";
        List<Books> booksList = Arrays.asList(new Books(), new Books());

        // When
        when(booksRepository.findByAuthorIgnoreCase(author)).thenReturn(booksList);
        List<Books> result = booksService.getBooksByAuthor(author);

        // Assert
        assertEquals(2, result.size());
        verify(booksRepository, times(1)).findByAuthorIgnoreCase(author);
    }

    @Test
    public void testGetBooksByAuthor_ThrowsResourceNotFoundException_WhenNoBooksFound() {
        // Given
        String author = "Unknown Author";

        // When
        when(booksRepository.findByAuthorIgnoreCase(author)).thenReturn(Collections.emptyList());

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> booksService.getBooksByAuthor(author));
        verify(booksRepository, times(1)).findByAuthorIgnoreCase(author);
    }

    @Test
    public void testGetBookById_ReturnsBook_WhenBookExists() throws Exception {
        // Given
        Long id = 1L;
        Books book = new Books();

        // When
        when(booksRepository.findById(id.toString())).thenReturn(Optional.of(book));
        Books result = booksService.getBookById(id);

        // Assert
        assertNotNull(result);
        verify(booksRepository, times(1)).findById(id.toString());
    }

    @Test
    public void testGetBookById_ThrowsResourceNotFoundException_WhenBookDoesNotExist() {
        // Given
        Long id = 1L;

        // When
        when(booksRepository.findById(id.toString())).thenReturn(Optional.empty());

        // Assert
        assertThrows(ResourceNotFoundException.class, () -> booksService.getBookById(id));
        verify(booksRepository, times(1)).findById(id.toString());
    }

    @Test
    public void testGetAllBooks_WithPageable_ReturnsPageOfBooks() {
        // Given
        Pageable pageable = mock(Pageable.class);
        List<Books> booksList = Arrays.asList(new Books(), new Books(), new Books());
        Page<Books> booksPage = new PageImpl<>(booksList, pageable, booksList.size());

        // When
        when(booksRepository.findAll(pageable)).thenReturn(booksPage);
        Page<Books> result = booksService.getAllBooks(pageable);

        // Assert
        assertEquals(3, result.getTotalElements());
        verify(booksRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testGetAllBooks_ReturnsListOfBooks() throws ResourceNotFoundException {
        // Given
        List<Books> booksList = Arrays.asList(new Books(), new Books(), new Books());

        // When
        when(booksRepository.findAll()).thenReturn(booksList);
        List<Books> result = booksService.getAllBooks();

        // Assert
        assertEquals(3, result.size());
        verify(booksRepository, times(1)).findAll();
    }

    @Test
    public void testSaveAllBooks_SavesBooksList() {
        // Given
        List<Books> booksList = Arrays.asList(new Books(), new Books());

        // When
        when(booksRepository.saveAll(booksList)).thenReturn(booksList);
        booksService.saveAllBooks(booksList);

        // Assert
        verify(booksRepository, times(1)).saveAll(booksList);
    }

}
