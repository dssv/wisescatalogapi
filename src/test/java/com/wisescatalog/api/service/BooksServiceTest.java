//package com.wisescatalog.api.service;
//
//import com.wisescatalog.api.dto.Books;
//import com.wisescatalog.api.repository.BooksRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static reactor.core.publisher.Mono.when;
//
//@RunWith(SpringRunner.class)
//public class BooksServiceTest {
//
//    @InjectMocks
//    private BooksService booksService;
//
//    @Mock
//    private BooksRepository booksRepository;
//
//    @BeforeEach
//    public void setup(){
//        MockitoAnnotations.openMocks(this);
//        booksRepository = Mockito.mock(BooksRepository.class);
//    }
//
//    @Test
//    public void teste () {
//        // Given
//        List<Books> booksList = new ArrayList<>();
//        Books books = new Books();
//        books.setId(1L);
//        books.setAuthor("Douglas Vasconcelos");
//        books.setTitle("Era uma vez");
//        booksList.add(books);
//
//        // When
//        Mockito.when(booksRepository.findByAuthorIgnoreCase("Douglas Vasconcelos")).thenReturn(booksList);
//        // Assert
//        assertEquals("Era uma vez", booksService.getBooksByAuthor("Douglas Vasconcelos").getFirst().getTitle());
//    }
//}
