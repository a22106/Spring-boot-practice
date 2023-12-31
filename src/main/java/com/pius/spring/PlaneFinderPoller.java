package com.pius.spring;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@EnableScheduling
@Component
public class PlaneFinderPoller {
    // 'org.springframework.boot:spring-boot-starter-webflux'
    private final WebClient client =
            WebClient.create("http://localhost:7634/aircraft");

    private final RedisConnectionFactory connectionFactory;
    private final RedisOperations<String, Aircraft> redisOperations;

    PlaneFinderPoller(RedisConnectionFactory connectionFactory,
                      RedisOperations<String, Aircraft> redisOperations){
        this.connectionFactory = connectionFactory;
        this.redisOperations = redisOperations;
    }

    @Scheduled(fixedRate = 1000)
    private void pollPlanes(){
        connectionFactory.getConnection().serverCommands().flushDb();

        client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(plane -> redisOperations.opsForValue()
                        .set(plane.getReg(), plane)
                );

        Objects.requireNonNull(redisOperations.opsForValue()
                        .getOperations()
                        .keys("*"))
                .forEach(plain -> System.out.println(redisOperations.opsForValue().get(plain)));
    }
}
