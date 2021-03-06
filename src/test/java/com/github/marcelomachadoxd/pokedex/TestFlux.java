package com.github.marcelomachadoxd.pokedex;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class TestFlux {

    @Test
    void TestFlux1(){
        Flux.empty();
    }

    @Test
    void testFlux2(){
        Flux<String> flux = Flux.just("Pokedex");
        flux.subscribe(System.out::println);
    }

    @Test
    void testFlux3(){
        Flux<String> flux = Flux.just("Charizard", "Blastoise", "Pikachu");
        flux.subscribe(System.out::println);
    }

}
