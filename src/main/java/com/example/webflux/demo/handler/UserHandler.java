package com.example.webflux.demo.handler;

import com.example.webflux.demo.bean.dto.UserInfoDTO;
import com.example.webflux.demo.handler.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.Nullable;

/**
 * @author lwk
 * @date 2019-07-10 10:55
 */
@Component
public class UserHandler {
    private final static Logger logger = LoggerFactory.getLogger(UserHandler.class);
    @Autowired
    private UserRepository userRepository;

    /**
     * 测试用方法
     *
     * @return
     */
    @Nullable
    public Mono<ServerResponse> helloUser(ServerRequest serverRequest) {

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just("成功"), String.class);
    }

    /**
     * 查询一个用户信息
     *
     * @param serverRequest
     * @return
     */
    @Nullable
    public Mono<ServerResponse> getUserInfo(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.findById(serverRequest.pathVariable("id")), UserInfoDTO.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * 查询列表
     *
     * @param serverRequest 传入的参数
     * @return
     */
    @Nullable
    public Mono<ServerResponse> userList(ServerRequest serverRequest) {
        Flux<UserInfoDTO> list = userRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(list, UserInfoDTO.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * 保存用户
     *
     * @param serverRequest
     * @return
     */
    @Nullable
    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) {
        // 相当于@ResponseBody
        Mono<UserInfoDTO> user = serverRequest.bodyToMono(UserInfoDTO.class);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.insert(user), UserInfoDTO.class);
    }

    /**
     * 验证参数后面再加
     */
//    private void validate(UserInfoDTO person) {
//        Errors errors = new BeanPropertyBindingResult(body, "person");
//        validator.validate(body, errors);
//        if (errors.hasErrors) {
//            throw new ServerWebInputException(errors.toString());
//        }
//    }

}
