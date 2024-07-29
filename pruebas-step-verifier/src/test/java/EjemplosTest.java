import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class EjemplosTest {

    @Test
    public void testFlux() {
        Flux<Integer> fluxInteger = Flux.just(1, 2, 3, 4, 5);

        //Creamos la prueba
        StepVerifier.create(fluxInteger)
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    public void testFluxString() {
        Flux<String> fluxString = Flux.just("Lia","Sebastián", "Gabriela", "Leah", "Alex", "Luis", "Lee")
                .filter(nombre -> nombre.length() >= 4)
                .map(String::toUpperCase);

        StepVerifier.create(fluxString)
                .expectNext("SEBASTIÁN")
                .expectNext("GABRIELA")
                .expectNext("LEAH")
                .expectNext("ALEX")
                .expectNext("LUIS")
                .expectComplete()
                .verify();
    }
}
