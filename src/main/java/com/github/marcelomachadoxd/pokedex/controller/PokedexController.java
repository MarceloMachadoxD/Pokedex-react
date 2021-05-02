package com.github.marcelomachadoxd.pokedex.controller;

import com.github.marcelomachadoxd.pokedex.model.Pokemon;
import com.github.marcelomachadoxd.pokedex.repository.PokedexRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


}
