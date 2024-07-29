package com.project.reactor;

import reactor.core.publisher.Flux;

public class Ejemplo04 {
    public static void main(String[] args) {
        Flux<String> flux = Flux.fromArray(new String[]{"A", "B", "C"});
        //flux.subscribe(System.out::println);
        flux.doOnNext(System.out::println).subscribe();
    }
}
