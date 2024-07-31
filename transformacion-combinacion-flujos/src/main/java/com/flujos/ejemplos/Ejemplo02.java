package com.flujos.ejemplos;

import reactor.core.publisher.Flux;

public class Ejemplo02 {
    public static void main(String[] args) {
        Flux.fromArray(new String[]{"Sebastián", "Jose", "Daniel", "Pedro", "Luis", "Juan"})
                .filter(nombre -> nombre.length() > 5)
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}
