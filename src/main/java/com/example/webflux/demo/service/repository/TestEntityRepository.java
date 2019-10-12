package com.example.webflux.demo.service.repository;

import com.example.webflux.demo.bean.dto.TestEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lwk
 * @date 2019-10-11 14:24
 */
@Repository
public interface TestEntityRepository extends ReactiveMongoRepository<TestEntity,String> {
}
