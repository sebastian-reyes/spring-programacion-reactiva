package com.project.reactor;

import reactor.core.publisher.Mono;

public class Ejemplo03 {
    public static void main(String[] args) {
        Mono<String> mono = Mono.fromSupplier(() -> {
            throw new RuntimeException("Excepción Ocurrida");
        });
        mono.subscribe(
                data -> System.out.println("Mensaje: " + data),
                error -> System.out.println("Error: " + error),
                () -> System.out.println("Completed"));
    }
}
