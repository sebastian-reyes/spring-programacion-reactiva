import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class EjemplosTests {

    @Test
    public void testTransformMap() {
        List<String> nombres = Arrays.asList("google", "facebook", "react", "java", "git", "pc");
        Flux<String> nombresFlux = Flux.fromIterable(nombres)
                .filter(nombre -> nombre.length() > 3)
                .map(String::toUpperCase)
                .log();

        StepVerifier.create(nombresFlux).expectNext("GOOGLE", "FACEBOOK", "REACT", "JAVA").verifyComplete();
    }

    @Test
    public void testTransformFlatMap() {
        List<String> nombres = Arrays.asList("google", "facebook", "react", "java", "git", "pc");
        Flux<String> nombresFlux = Flux.fromIterable(nombres)
                .filter(nombre -> nombre.length() > 3)
                .flatMap(nombre -> {
                    return Mono.just(nombre.toUpperCase());
                })
                .log();

        StepVerifier.create(nombresFlux).expectNext("GOOGLE", "FACEBOOK", "REACT", "JAVA").verifyComplete();
    }

    @Test
    public void testCombinarFlujosUsandoMerge() {
        Flux<String> nombres = Flux.just("Luis", "Sebastián", "Gabriela", "Pedro", "Jesús", "Pepe");
        Flux<String> roles = Flux.just("admin", "user", "security");

        Flux<String> fluxMerged = Flux.merge(nombres, roles);
        StepVerifier
                .create(fluxMerged)
                .expectNext("Luis", "Sebastián", "Gabriela", "Pedro", "Jesús", "Pepe", "admin", "user", "security")
                .verifyComplete();
    }

    @Test
    public void testCombinarFlujosUsandoMergeConDelay() {
        Flux<String> nombres = Flux
                .just("Luis", "Sebastián", "Gabriela", "Pedro", "Jesús", "Pepe")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> roles = Flux
                .just("admin", "user", "security")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxMerged = Flux.merge(nombres, roles).log();

        StepVerifier
                .create(fluxMerged)
                .expectSubscription()
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    public void testCombinarFlujosUsandoConcatConDelay() {
        Flux<String> nombres = Flux
                .just("Luis", "Sebastián", "Gabriela", "Pedro", "Jesús", "Pepe")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> roles = Flux
                .just("admin", "user", "security")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxConcat = Flux.concat(nombres, roles).log();

        StepVerifier
                .create(fluxConcat)
                .expectSubscription()
                .expectNext("Luis", "Sebastián", "Gabriela", "Pedro", "Jesús", "Pepe", "admin", "user", "security")
                .verifyComplete();
    }

    @Test
    public void testCombinarFlujosUsandoZipConDelay() {
        Flux<String> nombres = Flux
                .just("Luis", "Sebastián", "Gabriela", "Pedro", "Jesús", "Pepe")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> roles = Flux
                .just("admin", "user", "security")
                .delayElements(Duration.ofSeconds(1));

        Flux<String> fluxZip = Flux.zip(nombres, roles, (f1, f2) -> {
            return f1.concat(" ").concat(f2);
        }).log();

        StepVerifier
                .create(fluxZip)
                .expectNext("Luis admin", "Sebastián user", "Gabriela security")
                .verifyComplete();
    }
}
