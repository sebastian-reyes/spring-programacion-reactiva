package com.step.verifier;

import com.step.verifier.servicios.ServicioSencillo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@SpringBootTest
class PruebasServiciosVerifierApplicationTests {

    @Autowired
    ServicioSencillo servicioSencillo;

    @Test
    void testMono() {
        Mono<String> uno = servicioSencillo.buscarUno();
        StepVerifier.create(uno).expectNext("hola").verifyComplete();
    }

    @Test
    void testVarios() {
        Flux<String> varios = servicioSencillo.buscarVarios();
        StepVerifier.create(varios)
                .expectNext("hola", "como", "estas")
                .verifyComplete();
    }

    @Test
    void testVariosConDelay() {
        Flux<String> variosConDelay = servicioSencillo.buscarVariosConDelay();
        StepVerifier.create(variosConDelay)
                .expectNext("hola")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("como")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("estas")
                .thenAwait(Duration.ofSeconds(1))
                .verifyComplete();
    }
}
