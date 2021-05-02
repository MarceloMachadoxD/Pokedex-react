package com.github.marcelomachadoxd.pokedex.repository;

import com.github.marcelomachadoxd.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokedexRepository extends ReactiveMongoRepository<Pokemon, String> {
}
