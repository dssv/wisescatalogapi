package com.wisescatalog.api.controller;

import com.wisescatalog.api.dto.FakeIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.wisescatalog.api.service.FakeBooksService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeController {

    private final FakeBooksService fakeBooksService;

    @Autowired
    public FakeController(FakeBooksService fakeBooksService) {
        this.fakeBooksService = fakeBooksService;
    }

    @PostMapping(value = "/fake", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRandomFakeBooksList(@RequestBody FakeIndex index){
        this.fakeBooksService.insertRandomFakeBooksList(index.getIndex());
        return ResponseEntity.ok().build();
    }

}
