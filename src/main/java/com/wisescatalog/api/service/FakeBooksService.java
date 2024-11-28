package com.wisescatalog.api.service;

import com.github.javafaker.Faker;
import com.wisescatalog.api.dto.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeBooksService {

    private final BooksService booksService;

    @Autowired
    public FakeBooksService (BooksService booksService) {
        this.booksService = booksService;
    }

    private List<Books> createRandomFakeBooksList(int index) {
        int count = 0;
        List<Books> booksList = new ArrayList<>();

        do {
            Faker faker = new Faker();
            Books book = Books.builder()
                    .author(faker.book().author())
                    .genre(faker.book().genre())
                    .title(faker.book().title())
                    .build();

            booksList.add(book);
            count++;
        } while (count < index);

        return booksList;
    }

    public void insertRandomFakeBooksList(int index) {
        booksService.saveAllBooks(createRandomFakeBooksList(index));
    }

}
