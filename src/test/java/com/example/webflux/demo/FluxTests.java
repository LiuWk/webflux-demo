package com.example.webflux.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

/**
 * flux 测试StepVerifier
 *
 * @author lwk
 * @date 2019-07-09 14:31
 */
public class FluxTests {

    private Flux<Integer> generateFluxFrom1To6() {
        return Flux.just(1, 2, 3, 4, 5, 6);
    }

    private Mono<Integer> generateMonoWithError() {
        return Mono.error(new Exception("some error"));
    }

    @Test
    public void testViaStepVerifier() {
        StepVerifier.create(generateFluxFrom1To6())
                // 期望出现的是1~6数字
                .expectNext(1, 2, 3, 4, 5, 6)
                .expectComplete()
                .verify();
        StepVerifier.create(generateMonoWithError())
                .expectErrorMessage("some error")
                .verify();
    }

    @Test
    public void testHttpUtil() {
        String baseurl = "http://127.0.0.1:8082/test/test";

        Mono<JSONObject> str = WebClient.create()
                .get()
                .uri(baseurl)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(JSONObject.class));
        System.out.println(str.block());
    }

    @Test
    public void testCheckTextIsValid() {
        String baseurl = "http://analysis.baihe.com/inner/analysis/checkTextIsValid.json";
        Map<String, Object> originalParams = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        originalParams.put("text", "一起去吃饭高级餐厅卖淫一起去工作吧顺便大篮球");
        originalParams.put("textType", "2");
        params.put("params", JSON.toJSONString(originalParams));

       /* Mono<String> res = WebClient.create()
                .post()
                .uri(baseurl)
                .body(Flux.just(params), Map.class)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class));*/
       WebClient.create()
               .get()
               .uri(baseurl)
               .exchange()
               .log()
               .block();
    }

}

