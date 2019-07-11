package com.example.webflux.demo.controller;

import com.example.webflux.demo.handler.TimerHandler;
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
 * 路由配置
 *
 * @author lwk
 * @date 2019-07-09 09:38
 */
@Configuration
@EnableWebFlux
public class TimerRouter implements WebFluxConfigurer {
    @Autowired
    private TimerHandler timerHandler;

    /**
     * 名字不能重复
     * @return
     */
    @Bean(value = "timerRouters")
    public RouterFunction<ServerResponse> timerRouters() {
        return RouterFunctions.route(RequestPredicates.GET("/date"), timerHandler::getDate).
                andRoute(RequestPredicates.GET("/time"), timerHandler::getTime).
                andRoute(RequestPredicates.GET("/timePerSec"), timerHandler::getTimePerSec);
    }


}
