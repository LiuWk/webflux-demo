package com.example.webflux.demo.service.impl;

import com.example.webflux.demo.bean.dto.UserInfoDTO;
import com.example.webflux.demo.service.UserService;
import com.example.webflux.demo.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author lwk
 * @date 2019-10-10 09:50
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserInfoDTO> findUserInfoDTOByPhoneEquals(String phone) {
        return userRepository.findUserInfoDTOByPhoneEquals(phone);
    }

    @Override
    public Mono<UserInfoDTO> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<UserInfoDTO> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Flux<UserInfoDTO> insert(Mono<UserInfoDTO> user) {
        user = user.map(userInfoDTO -> {
            userInfoDTO.setCreateDate(new Date());
            return userInfoDTO;
        });
        return userRepository.insert(user);
    }
}
