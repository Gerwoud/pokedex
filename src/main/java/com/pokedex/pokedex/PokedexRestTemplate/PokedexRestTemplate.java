package com.pokedex.pokedex.PokedexRestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class PokedexRestTemplate {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        return new RestTemplate(factory);
    }

    public static JsonNode fetchPokemonAPI(String url) {
        String resp = getRestTemplate().getForObject(url, String.class);
        if (resp == null || resp.contains("\"detail\":\"Not found\"")) {
            throw new RuntimeException("Resource not found: " + url);
        }

        try {
            JsonNode node = objectMapper.readTree(resp);
            return node;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
