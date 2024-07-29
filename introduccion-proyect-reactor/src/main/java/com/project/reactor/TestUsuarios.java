package com.project.reactor;


import com.project.reactor.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class TestUsuarios {

    private static final Logger LOG = LoggerFactory.getLogger(TestUsuarios.class);

    public static void main(String[] args) {
        Flux<String> nombres = Flux.just("Sebastián Reyes", "Gabriela Odría", "Leah Reyes", "Esteban Quito");
        Flux<Usuario> usuarios = nombres.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
                .filter(usuario -> !usuario.getApellidos().equalsIgnoreCase("Quito"))
                .doOnNext(usuario -> {
                    if (usuario == null) {
                        throw new RuntimeException("Los nombres no pueden estar vacíos");
                    }
                    System.out.println(usuario.getNombres().concat(" ").concat(usuario.getApellidos()));
                })
                .map(usuario -> {
                    String nombre = usuario.getNombres().toLowerCase();
                    usuario.setNombres(nombre);
                    return usuario;
                });
        usuarios.subscribe(e -> LOG.info(e.toString()), error -> LOG.error(error.getMessage()), new Runnable() {
            @Override
            public void run() {
                LOG.info("Se ha finalizado la ejecución del Observable con éxito.");
            }
        });
    }
}
