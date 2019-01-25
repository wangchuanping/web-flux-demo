package com.example.mine.webfluxdemo.controller;

import com.example.mine.webfluxdemo.bean.User;
import com.example.mine.webfluxdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public Mono<User> queryById(@PathVariable Integer id){
       return userService.queryById(id);
    }
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> queryList(){
        //userService.queryList().delayElements(Duration.ofSeconds(2));这个是延迟
        //就是查到一条数据就返回一条数据，所以，使用webFlux编程就得看整体的业务需求。
        //如果从前端请求到数据访问层都是响应式的，那么久可以整体改造，否则和普通的MVC没什么区别
        return userService.queryList();
    }
    @GetMapping("/remove/{id}")
    public Mono<Integer> removeById(@PathVariable Integer id){
        return userService.removeById(id);
    }


}
