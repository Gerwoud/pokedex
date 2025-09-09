package com.pokedex.pokedex.theme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.style.Theme;
import org.springframework.shell.style.ThemeSettings;

@Configuration
public class PokedexThemeConfig {

    @Bean
    Theme pokedexTheme() {
        return new Theme() {
            @Override
            public String getName() {
                return "pokedex";
            }

            @Override
            public ThemeSettings getSettings() {
                return new PokedexTheme();
            }
        };
    }
}
