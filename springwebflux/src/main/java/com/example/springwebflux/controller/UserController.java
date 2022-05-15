package com.example.springwebflux.controller;

import com.example.springwebflux.entity.UserEntity;
import com.example.springwebflux.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping( "/user/{id}")
    public Mono<UserEntity> geetUserId(@PathVariable String id) {
        return  userService.selectById(id);
    }

    //查询所有
    @GetMapping( "/user")
    public Flux<UserEntity> getUsers(UserEntity user) {
        return  userService.selectList(user);
    }
    //添加
    @PostMapping( "/saveuser")
    public Mono<Void> saveUser(UserEntity user) {
        Mono<UserEntity> userMono = Mono. just (user);
        return  userService.insert(userMono);
    }



}
