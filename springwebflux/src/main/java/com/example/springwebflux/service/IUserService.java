package com.example.springwebflux.service;

import com.example.springwebflux.entity.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<Void> insert(Mono<UserEntity> monoUser);

    Mono<UserEntity> selectById(String id);

    Flux<UserEntity> selectList(UserEntity monoUser);
}
