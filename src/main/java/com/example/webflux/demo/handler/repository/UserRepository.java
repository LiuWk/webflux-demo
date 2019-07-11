package com.example.webflux.demo.handler.repository;

import com.example.webflux.demo.bean.dto.UserInfoDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * mongo 用户操作
 *
 * @author lwk
 * @date 2019-07-10 14:47
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<UserInfoDTO, String> {

    /**
     * 根据phone查询
     *
     * @return UserInfoDTO
     */
    UserInfoDTO findUserInfoDTOByPhone(String phone);
}
