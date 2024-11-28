package com.wisescatalog.api.controller;

import com.wisescatalog.api.dto.Books;
import com.wisescatalog.api.exception.ResourceNotFoundException;
import com.wisescatalog.api.service.BooksService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BooksController.class)
public class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BooksService booksService;

    @InjectMocks
    private BooksController booksController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @SneakyThrows
    @Test
    public void testGetBooks_ReturnsAllBooks_WhenNoPaginationParamsProvided() throws Exception {
        // Given
        Books book1 = new Books();
        book1.setId(1L);
        book1.setTitle("Book One");
        Books book2 = new Books();
        book2.setId(2L);
        book2.setTitle("Book Two");

        List<Books> booksList = Arrays.asList(book1, book2);

        // When
        when(booksService.getAllBooks()).thenReturn(booksList);

        // Assert
        mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].title", is("Book One")))
                .andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[1].title", is("Book Two")));
    }

    @SneakyThrows
    @Test
    public void testGetBooks_ReturnsPagedBooks_WhenPaginationParamsProvided() throws Exception {
        // Given
        int page = 0;
        int size = 2;
        Pageable pageable = PageRequest.of(page, size);
        Books book1 = new Books();
        book1.setId(1L);
        book1.setTitle("Book One");
        Books book2 = new Books();
        book2.setId(2L);
        book2.setTitle("Book Two");
        List<Books> booksList = Arrays.asList(book1, book2);
        Page<Books> booksPage = new PageImpl<>(booksList, pageable, booksList.size());

        // When
        when(booksService.getAllBooks(pageable)).thenReturn(booksPage);

        // Assert
        mockMvc.perform(get("/books")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id", is("1")))
                .andExpect(jsonPath("$.content[0].title", is("Book One")))
                .andExpect(jsonPath("$.content[1].id", is("2")))
                .andExpect(jsonPath("$.content[1].title", is("Book Two")));
    }

    @SneakyThrows
    @Test
    public void testGetBookById_ReturnsBook_WhenBookExists() throws Exception {
        // Given
        Long id = 1L;
        Books book = new Books();
        book.setId(id);
        book.setTitle("Book One");

        // When
        when(booksService.getBookById(id)).thenReturn(book);

        // Assert
        mockMvc.perform(get("/books")
                        .param("id", id.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.toString())))
                .andExpect(jsonPath("$.title", is("Book One")));
    }

    @SneakyThrows
    @Test
    public void testGetBooksByGenre_ReturnsBooksList_WhenBooksExist() {
        // Given
        String genre = "Fantasy";
        Books book1 = new Books();
        book1.setId(1L);
        book1.setTitle("Fantasy Book One");
        book1.setGenre(genre);
        Books book2 = new Books();
        book2.setId(2L);
        book2.setTitle("Fantasy Book Two");
        book2.setGenre(genre);

        List<Books> booksList = Arrays.asList(book1, book2);

        // When
        when(booksService.getBooksByGenre(genre)).thenReturn(booksList);

        // Assert
        mockMvc.perform(get("/books/genre")
                        .param("genre", genre)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].genre", is(genre)))
                .andExpect(jsonPath("$[1].genre", is(genre)));
    }

    @SneakyThrows
    @Test
    public void testGetBooksByAuthor_ReturnsBooksList_WhenBooksExist() {
        // Given
        String author = "Author Name";
        Books book1 = new Books();
        book1.setId(1L);
        book1.setTitle("Book One");
        book1.setAuthor(author);
        Books book2 = new Books();
        book2.setId(2L);
        book2.setTitle("Book Two");
        book2.setAuthor(author);

        List<Books> booksList = Arrays.asList(book1, book2);

        // When
        when(booksService.getBooksByAuthor(author)).thenReturn(booksList);

        // Assert
        mockMvc.perform(get("/books/author")
                        .param("author", author)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].author", is(author)))
                .andExpect(jsonPath("$[1].author", is(author)));
    }

    @SneakyThrows
    @Test
    public void testGetBookById_ReturnsNotFound_WhenBookDoesNotExist() throws Exception {
        // Given
        Long id = 1L;

        // When
        when(booksService.getBookById(id)).thenThrow(new ResourceNotFoundException());

        // Assert
        mockMvc.perform(get("/books")
                        .param("id", id.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
