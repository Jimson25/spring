package com.example.springwebflux.handler;

import com.example.springwebflux.entity.UserEntity;
import com.example.springwebflux.service.IUserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserHandler {
    private final IUserService userService;

    public UserHandler(IUserService userService) {
        this.userService = userService;
    }

    //根据 id 查询
    // 这里的请求对象不是servletRequest而是serverRequest
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<UserEntity> userMono = userService.selectById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return userMono.flatMap(e ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(e))
                .switchIfEmpty(notFound);
    }

    //查询所有
    public Mono<ServerResponse> getAllUsers(UserEntity entity) {
        //调用 service 得到结果
        Flux<UserEntity> users = this.userService.selectList(entity);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, UserEntity.class);
    }

    //添加
    public Mono<ServerResponse> saveUser(ServerRequest request) {
        //得到 user 对象
        Mono<UserEntity> userMono = request.bodyToMono(UserEntity.class);
        return ServerResponse.ok().build(this.userService.insert(userMono));
    }


}
