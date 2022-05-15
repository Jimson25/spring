package com.example.springwebflux.service.impl;

import com.example.springwebflux.entity.UserEntity;
import com.example.springwebflux.mapper.IUserMapper;
import com.example.springwebflux.mapper.impl.IUserMapperImpl;
import com.example.springwebflux.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements IUserService {
    private IUserMapper userMapper =new IUserMapperImpl();

    @Override
    public Mono<Void> insert(Mono<UserEntity> monoUser) {
        return monoUser.doOnNext(entity -> {
            userMapper.insert(entity);
        }).thenEmpty(Mono.empty());
    }

    @Override
    public Mono<UserEntity> selectById(String id) {
        return Mono.justOrEmpty(userMapper.selectById(id));
    }

    @Override
    public Flux<UserEntity> selectList(UserEntity userEntity) {
        return Flux.fromIterable(userMapper.selectListByStatus(userEntity));
    }

}
