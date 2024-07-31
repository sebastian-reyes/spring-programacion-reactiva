package com.flujos.ejemplos;

import reactor.core.publisher.Flux;

public class Ejemplo01 {
    public static void main(String[] args) {
        Flux.fromArray(new String[]{"Sebastián", "Jose", "Daniel", "Pedro", "Luis", "Juan"})
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}
