package com.contrapresion.ejemplos;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

public class Ejemplo01 {
    public static void main(String[] args) {
        Flux<String> cuidades = Flux.fromIterable(
                new ArrayList<>(Arrays.asList("Lima", "Breña", "Pueblo Libre", "Los Olivos", "San Juan de Miraflores"))
        );
        cuidades.log().subscribe();
    }
}
