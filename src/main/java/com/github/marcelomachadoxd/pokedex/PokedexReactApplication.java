package com.github.marcelomachadoxd.pokedex;

import com.github.marcelomachadoxd.pokedex.model.Pokemon;
import com.github.marcelomachadoxd.pokedex.repository.PokedexRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexReactApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokedexReactApplication.class, args);
    }

    @Bean
    CommandLineRunner init (ReactiveMongoOperations operations,
                            PokedexRepository repository){

        return args -> {
            Flux<Pokemon> pokeFlux = Flux.just(
                new Pokemon(null, "Bulbassauro", "Semente", "OverGrow", 6.09),
                new Pokemon(null, "Charizard", "Fogo", "Blaze", 90.05),
                new Pokemon(null, "Caterpie", "Minhoca", "Poeira do Escudo", 2.09),
                new Pokemon(null, "Blastoise", "Marisco", "Torrente", 6.09))
                .flatMap(repository::save);
            pokeFlux
                .thenMany(repository.findAll())
                .subscribe(System.out::println);
        };

    }
}
