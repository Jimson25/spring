package com.example.springwebflux;

import com.example.springwebflux.entity.UserEntity;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class Client {
    public static void main(String[] args) {
        //调用服务器地址
        WebClient webClient = WebClient.create("http://127.0.0.1:8088");
        //根据 id 查询
        String id = "1";
        UserEntity userresult = webClient.get()
                .uri("/users/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserEntity.class)
                .block();
        assert userresult != null;
        System.out.println(userresult.getUsername());

        //查询所有
        Flux<UserEntity> results = webClient.get()
                .uri("/use rs")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(UserEntity.class);

        results.map(UserEntity::getUsername)
                .buffer().doOnNext(System.out::println).blockFirst();
    }
}