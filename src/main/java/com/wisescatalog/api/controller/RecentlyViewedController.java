package com.wisescatalog.api.controller;

import com.wisescatalog.api.dto.Books;
import com.wisescatalog.api.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecentlyViewedController {

    private final RedisService redisService;

    @Autowired
    public RecentlyViewedController (RedisService redisService){
        this.redisService = redisService;
    }

    @GetMapping("/viewed")
    public ResponseEntity<List<Books>> livrosRecentes() {
            return ResponseEntity.ok(redisService.getBooksRecentlyViewed());
    }
}
