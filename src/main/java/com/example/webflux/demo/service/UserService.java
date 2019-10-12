package com.example.webflux.demo.service;

import com.example.webflux.demo.bean.dto.UserInfoDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author lwk
 * @date 2019-07-26 18:07
 */
public interface UserService {
    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    Mono<UserInfoDTO> findUserInfoDTOByPhoneEquals(String phone);

    /**
     * 根据
     * @param id
     * @return
     */
    Mono<UserInfoDTO> findById(String id);

    Flux<UserInfoDTO> findAll();

    Flux<UserInfoDTO> insert(Mono<UserInfoDTO> user);
}
