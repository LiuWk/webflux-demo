package com.example.webflux.demo.service.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapRepositoryTest {
    @Autowired
    private MapRepository mapRepository;

    @Test
    public void find(){
//        Flux<Map> list = mapRepository.findAll(Sort.by(Sort.Direction.DESC, "country"));
//        list.subscribe(System.out::println);

//        Map<String,String> map = new HashMap<>(1);
//        map.put("company","阿里");
//        Mono<Map<String, String>> t1 = mapRepository.findOne(Example.of(map));
//        t1.subscribe(System.out::println);
    }

    @Test
    public void save(){
        Map<String,String> map1 = new HashMap<>(2);
        map1.put("id","1212");
        map1.put("name","张三");
        Mono<Map<String, String>> r = mapRepository.save(map1);
        r.subscribe(System.out::println);
    }
}