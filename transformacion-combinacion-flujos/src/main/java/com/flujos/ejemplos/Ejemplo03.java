package com.flujos.ejemplos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo03 {
    public static void main(String[] args) {
        Flux.fromArray(new String[]{"Sebastián", "Jose", "Daniel", "Pedro", "Luis", "Juan"})
                .flatMap(Ejemplo03::ponerNombreModificadoEnMono)
                .subscribe(System.out::println);
    }

    private static Mono<String> ponerNombreModificadoEnMono(String nombre) {
        return Mono.just(nombre.concat(" modificado"));
    }
}
