//package com.wisescatalog.api.service;
//
//
//import com.wisescatalog.api.dto.Books;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class RecentlyViewedService {
//
//    @Autowired
//    private RedisTemplate<String, Books> redisTemplate;
//
//    private static final int LIMITE_VISUALIZACOES = 10;
//
//    public void registerView(Books book) {
//        String chave = "visualizacoes: user1";
//
//        // Adiciona o livro ao início da lista
//        redisTemplate.opsForList().remove(chave, 1, book); // Remove se já existir
//        redisTemplate.opsForList().leftPush(chave, book);
//
//        // Limita o tamanho da lista
//        redisTemplate.opsForList().trim(chave, 0, LIMITE_VISUALIZACOES - 1);
//
//        // Define o tempo de expiração (opcional)
//        redisTemplate.expire(chave, 50, TimeUnit.MILLISECONDS);
//    }
//
//    public List<Books> getRecentlyViewedBooks() {
//        String chave = "visualizacoes: user1";
//        return redisTemplate.opsForList().range(chave, 0, LIMITE_VISUALIZACOES - 1);
//    }
//}