package com.example.mine.webfluxdemo.service;

import com.example.mine.webfluxdemo.bean.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private static final Map<Integer,User> map = new HashMap<>();
    static {
        map.put(1,new User(1,"AA",1));
        map.put(2,new User(2,"BB",2));
        map.put(3,new User(3,"CC",1));
        map.put(4,new User(4,"DD",2));
        map.put(5,new User(5,"EE",1));
        map.put(6,new User(6,"FF",2));
    }

    public Flux<User> queryList(){
        Collection<User> values = map.values();
        return Flux.fromIterable(values);
    }

    public Mono<User> queryById(Integer id){
        return Mono.justOrEmpty(map.get(id));
    }

    public Mono<Integer> removeById(Integer id){
        map.remove(id);
        return Mono.just(1);
    }

}
