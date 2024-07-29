package com.step.verifier.servicios;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ServicioSencillo {
    public Mono<String> buscarUno() {
        return Mono.just("hola");
    }

    public Flux<String> buscarVarios() {
        return Flux.just("hola", "como", "estas");
    }

    public Flux<String> buscarVariosConDelay() {
        return Flux.just("hola", "como", "estas").delaySequence(Duration.ofSeconds(3));
    }
}
