package reactor;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lwk
 * @date 2019-07-26 10:49
 */
public class FluxTest {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("1", "3", "-1", "12", "0", "7");
    }
}
