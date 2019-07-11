package com.example.webflux.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.webflux.demo.bean.dto.UserInfoDTO;
import com.example.webflux.demo.bean.response.Response;
import com.example.webflux.demo.constant.Code;
import com.example.webflux.demo.handler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author lwk
 * @date 2019-07-08 15:49
 */
@RestController
@RequestMapping(value = "test")
public class WebfluxTestController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("test")
    public Mono test() {
        return Mono.just(new Response(Code.SUCCESS, "test 成功", "啊啊啊啊啊啊啊"));
    }

    @PostMapping("objectMono")
    public Mono<Object> objectMono() {
        List<String> list = Arrays.asList("12", "3", "11", "44", "10");
        return Mono.just(list);
    }

    @PostMapping("saveUser")
    public Mono<UserInfoDTO> saveUser(@RequestBody UserInfoDTO userInfoDTO) {
        return userRepository.insert(userInfoDTO);
    }


    public static void main(String[] args) {

        Flux.just(1, 2, 3, 445, 5, 6, 9).subscribe(System.out::println, System.err::println,
                () -> System.out.println("Complete!"));

        long timeMillis = System.currentTimeMillis();
        System.out.println("webflux() start");
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux data--" + i;
        }));
        System.out.println("webflux() end use time "+(System.currentTimeMillis() - timeMillis)+" ms");
    }

}
