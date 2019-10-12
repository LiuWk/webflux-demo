package com.example.webflux.demo.controller;

import com.example.webflux.demo.bean.dto.TestEntity;
import com.example.webflux.demo.bean.dto.UserInfoDTO;
import com.example.webflux.demo.bean.response.Response;
import com.example.webflux.demo.constant.Code;
import com.example.webflux.demo.service.repository.MapRepository;
import com.example.webflux.demo.service.repository.TestEntityRepository;
import com.example.webflux.demo.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private TestEntityRepository testEntityRepository;
    @Autowired
    private MapRepository mapRepository;

    @GetMapping(value = "test")
    public Mono test() {
        System.out.println("test");
        return Mono.just(new Response(Code.SUCCESS, "test 成功", "啊啊啊啊啊啊啊"));
    }

    @PostMapping(value = "objectMono")
    public Mono<Object> objectMono() {
        List<String> list = Arrays.asList("12", "3", "11", "44", "10");
        return Mono.just(list);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "saveUser")
    public Mono<UserInfoDTO> saveUser(@RequestBody UserInfoDTO userInfoDTO) {
        userInfoDTO.setCreateDate(new Date());
        return userRepository.insert(userInfoDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "saveEnt")
    public Mono<TestEntity> saveEnt(@RequestBody TestEntity testEntity) {
        return testEntityRepository.insert(testEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "saveMap")
    public Mono<Map> saveMap(@RequestBody Map map) {
        return mapRepository.insert(map);
    }

    @GetMapping(value = "getUser/phone/{phone}")
    public Mono<ResponseEntity<UserInfoDTO>> getUser(@PathVariable("phone") String phone) {
        return userRepository.findUserInfoDTOByPhoneEquals(phone)
                .map(o -> new ResponseEntity<>(o, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    public static void main(String[] args) {

        Flux.just(1, 2, 3, 445, 5, 6, 9).subscribe(System.out::print, System.err::println,
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
        System.out.println("webflux() end use time " + (System.currentTimeMillis() - timeMillis) + " ms");
        // 真正用这个结果的时候才会阻塞5s
        System.out.println(result.blockFirst());

        System.out.println("最大内存" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
        System.out.println("可用内存" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println("已使用内存" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
    }

}
