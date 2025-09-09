package com.pokedex.pokedex.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.pokedex.pokedex.PokedexRestTemplate.PokedexRestTemplate;
import com.pokedex.pokedex.dto.PokedexDTO;
import org.jline.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PokedexController {

    private int currentId;
    private static final String API = "https://pokeapi.co/api/v2/pokemon/";

    public PokedexController() {

    }

    public PokedexDTO getPokemon(String name) {

        JsonNode node = PokedexRestTemplate.fetchPokemonAPI(API+name.toLowerCase());
        final String speciesAPI = node.get("species").get("url").asText();

        JsonNode speciesNode = PokedexRestTemplate.fetchPokemonAPI(speciesAPI);

        String flavorText = "";
        JsonNode flavorEntries = speciesNode.get("flavor_text_entries");

        if (flavorEntries.isArray()) {
            Optional<JsonNode> flavorText2 = StreamSupport.stream(flavorEntries.spliterator(), false)
                    .filter(e -> e.get("language").get("name").asText().equals("en"))
                    .findFirst();

            if (flavorText2.isPresent()) {
                flavorText = flavorText2.get().get("flavor_text").asText();
            }
        }

        JsonNode typeNode = node.get("types");
        List<String> types = new ArrayList<>();
        if (typeNode.isArray()) {
            for (int i = 0; i < typeNode.size(); i++) {
                types.add(typeNode.get(i).get("type").get("name").asText());
            }
        }

        currentId = node.get("id").asInt();

        JsonNode prevNode = null;
        if (currentId - 1 > 0) {
            prevNode = PokedexRestTemplate.fetchPokemonAPI(API + (currentId - 1));
        }
        JsonNode nextNode = PokedexRestTemplate.fetchPokemonAPI(API + (currentId + 1));

        if (prevNode != null) {
            return new PokedexDTO(currentId, node.get("name").asText(), flavorText, prevNode.get("name").asText(), nextNode.get("name").asText(), types);
        }

        return new PokedexDTO(currentId, node.get("name").asText(), flavorText, null, nextNode.get("name").asText(), types);

    }

    public PokedexDTO nextPokemon(int jump) throws JsonProcessingException {
        currentId += jump;
        return getPokemon(String.valueOf(currentId));
    }

    public List<String> getList() {
        List<String> allPokemon = new ArrayList<>();
        String nextUrl = "https://pokeapi.co/api/v2/pokemon/";

        while (nextUrl != null) {
            JsonNode response = PokedexRestTemplate.fetchPokemonAPI(nextUrl);
            JsonNode results = response.get("results");

            if (results.isArray()) {
                List<String> pagePokemon = StreamSupport.stream(results.spliterator(), false)
                        .map(e -> e.get("name").asText())
                        .collect(Collectors.toList());
                allPokemon.addAll(pagePokemon);
            }

            nextUrl = response.has("next") && !response.get("next").isNull()
                    ? response.get("next").asText()
                    : null;
        }

        return allPokemon;
    }

    public List<PokedexDTO> getPokedex(int maxPokemon) {
        List<PokedexDTO> allPokemon = new ArrayList<>();
        String nextUrl = "https://pokeapi.co/api/v2/pokemon/";

        while (nextUrl != null && allPokemon.size() < maxPokemon) {
            System.out.println("parsing pokemon from: " + nextUrl);
            JsonNode response = PokedexRestTemplate.fetchPokemonAPI(nextUrl);
            JsonNode results = response.get("results");

            if (results.isArray()) {
                List<PokedexDTO> pagePokemon = StreamSupport.stream(results.spliterator(), true)
                        .map(e -> getPokemon(e.get("name").asText()))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                for (PokedexDTO pokemon : pagePokemon) {
                    if (allPokemon.size() >= maxPokemon) {
                        break;
                    }
                    allPokemon.add(pokemon);
                }
            }

            if (allPokemon.size() < maxPokemon) {
                nextUrl = response.has("next") && !response.get("next").isNull()
                        ? response.get("next").asText()
                        : null;
            } else {
                nextUrl = null;
            }
        }

        return allPokemon;
    }

    private int selected = 0;
}