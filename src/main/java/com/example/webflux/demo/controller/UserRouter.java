package com.example.webflux.demo.controller;

import com.example.webflux.demo.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 用户接口相关路由，业务逻辑实现
 *
 * @author lwk
 * @date 2019-07-10 10:28
 */
@Configuration
@EnableWebFlux
public class UserRouter implements WebFluxConfigurer {

    @Autowired
    private UserHandler userHandler;

    /**
     * 用户相关路由
     *
     * @return
     */
    @Bean(value = "userRouters")
    public RouterFunction<ServerResponse> userRouters() {
        return RouterFunctions.route(RequestPredicates.GET("/user/hello"), userHandler::helloUser)
                .andRoute(RequestPredicates.GET("/user/userList"), userHandler::userList)
                .andRoute(RequestPredicates.POST("/user/save"),userHandler::saveUser)
                .andRoute(RequestPredicates.GET("/user/{id}"),userHandler::getUserInfo);
    }

}
