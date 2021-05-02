package com.github.marcelomachadoxd.pokedex.controller;

import com.github.marcelomachadoxd.pokedex.model.Pokemon;
import com.github.marcelomachadoxd.pokedex.repository.PokedexRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/pokemons")
public class PokedexController {

    private PokedexRepository repository;

    public PokedexController(PokedexRepository repository){
        this.repository = repository;
    }

    @GetMapping("/")
    public Flux<Pokemon> getAllPokemon(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String id){
        return repository.findById(id)
            .map(pokemon -> ResponseEntity.ok(pokemon))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon){
        return repository.save(pokemon);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable(value = "id")
                                                       String id,
                                                       @RequestBody Pokemon pokemon){
        return repository.findById(id)
            .flatMap(existingPokemon -> {
            existingPokemon.setNome(pokemon.getNome());
            existingPokemon.setCategoria(pokemon.getCategoria());
            existingPokemon.setPeso(pokemon.getPeso());
            return repository.save(existingPokemon);

            }
        ).map(updatePokemon -> ResponseEntity.ok(updatePokemon))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePokemon(@PathVariable(value = "id")
                                                    String id){

        return repository.findById(id)
            .flatMap(existingPokemon ->
                repository.delete(existingPokemon)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build())
            );

    }

    @DeleteMapping
    public Mono<Void> deleteAllPokemons(){
      return repository.deleteAll();
    }



}
