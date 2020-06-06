package com.discover.app;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class AppApplicationTests {

    //    String wed = (String a) -> a;

    List<String> names = Arrays.asList("Mike", "Jason", "Joe", "Mason");

    @Test
    public void contextLoads() {
        Flux<String> wea =
                Flux.just(state(), "sports", "news")
                        .concatWith(Mono.just("man"))
                        .log()
                        .filter(e -> e.equalsIgnoreCase("man") || e.equalsIgnoreCase("news"));
        wea.subscribe(System.out::println);
    }

    @Test
    public void testFluxwithoutError() {
        Flux<String> wea = Flux.just(state(), "sports", "news").concatWith(Mono.just("man"));

        StepVerifier.create(wea.log())
                .expectNext(state(), "sports", "news", "man")
                .verifyComplete();
    }

    @Test
    public void test_FluxwithError() {
        Flux<String> wea =
                Flux.just(state(), "sports", "news")
                        .concatWith(Mono.error(new Exception("exception occured")));

        StepVerifier.create(wea)
                .expectNext(state(), "sports", "news")
                .expectError(Exception.class)
                .verify();
    }

    @Test
    public void test_Froniterable() {
        Flux<String> namess = Flux.fromIterable(names).filter(s -> s.startsWith("M")).log();

        StepVerifier.create(namess).expectNext("Mike", "Mason").verifyComplete();
    }

    @Test
    public void test_FromStream() {
        Flux<String> namess = Flux.fromStream(names.stream()).log();

        StepVerifier.create(namess).expectNext("Mike", "Jason", "Joe", "Mason").verifyComplete();
    }

    @Test
    public void test_transfromUsingMapAndFilter() {
        Flux<String> namess =
                Flux.fromStream(names.stream())
                        .filter(s -> s.length() > 4)
                        .map(s -> s.toUpperCase())
                        .log();
        String name = "sss" + "xxxxxx";
        String we = "aaaa";

        StepVerifier.create(namess).expectNext("JASON", "MASON").verifyComplete();
    }

    @Test
    public void transformUsingFlatMap() {}

    protected String state() {
        return "weather";
    }
}
