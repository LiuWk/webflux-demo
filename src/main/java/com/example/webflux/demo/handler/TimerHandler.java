package com.example.webflux.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 定时器路由
 *
 * @author lwk
 * @date 2019-07-09 09:38
 */
@Component
public class TimerHandler {
    private final static Logger logger = LoggerFactory.getLogger(TimerHandler.class);

    @Nullable
    public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
        logger.info("getTime serverRequest methodName:{}", serverRequest.methodName());
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }

    @Nullable
    public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
        logger.info("getDate serverRequest methodName:{}", serverRequest.methodName());
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Mono.just("Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())), String.class);
    }

    @Nullable
    public Mono<ServerResponse> getTimePerSec(ServerRequest serverRequest) {
        logger.info("getDate getTimePerSec methodName:{}", serverRequest.methodName());
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Flux.interval(Duration.ofSeconds(1)).
                map(str -> DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())), String.class);
    }
}
