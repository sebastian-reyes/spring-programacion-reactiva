package com.project.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Ejemplo01 {
    public static void main(String[] args) {

        List<Integer> elementosFromMono = new ArrayList<>();
        List<Integer> elementosFromFlux = new ArrayList<>();

        //Creamos Mono y Flux
        Mono<Integer> mono = Mono.just(21);
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        mono.subscribe(elementosFromMono::add);
        flux.subscribe(elementosFromFlux::add);

        System.out.println(elementosFromMono);
        System.out.println(elementosFromFlux);
    }
}
