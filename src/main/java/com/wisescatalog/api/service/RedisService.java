package com.wisescatalog.api.service;

import com.wisescatalog.api.config.RedisConfig;
import com.wisescatalog.api.dto.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisConfig redisConfig;

    private static final int LIMITE_VISUALIZACOES = 10;

    @Autowired
    public RedisService(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    public void registerView(Books books) {
        String chave = "1"; // TODO: ajustar quando criar uma autenticação por usuário;

        // Adiciona o Books ao início da lista
        redisConfig.redisTemplate().opsForList().remove(chave, 1, books); // Remove se já existir
        redisConfig.redisTemplate().opsForList().leftPush(chave, books);

        // Limita o tamanho da lista
        redisConfig.redisTemplate().opsForList().trim(chave, 0, LIMITE_VISUALIZACOES - 1);

        // Define o tempo de expiração (opcional)
        redisConfig.redisTemplate().expire(chave, 60, TimeUnit.MILLISECONDS);
    }

    public List<Books> getBooksRecentlyViewed() {
        String chave = "1"; // TODO: ajustar quando criar uma autenticação por usuário;
        return redisConfig.redisTemplate().opsForList().range(chave, 0, LIMITE_VISUALIZACOES - 1);
    }
}

