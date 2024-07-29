package com.project.reactor;

import reactor.core.publisher.Mono;

public class Ejemplo02 {

    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hola");
        mono.subscribe(
                data -> System.out.println("Mensaje: " + data),
                error -> System.out.println("Error: " + error),
                () -> System.out.println("Completed"));
    }
}
