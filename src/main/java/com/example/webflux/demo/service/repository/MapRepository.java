package com.example.webflux.demo.service.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author lwk
 * @date 2019-10-11 14:24
 */
@Repository
public interface MapRepository extends ReactiveMongoRepository<Map<String,String>,String> {
}
