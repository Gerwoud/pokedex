package com.pokedex.pokedex.dto;

import java.util.List;

public record PokedexDTO(
        int id,
        String name,
        String flavortext,
        String prevPokemon,
        String nextPokemon,
        List<String> types,
        List<String> abilities,
        List<StatDTO> stats
) {
}
