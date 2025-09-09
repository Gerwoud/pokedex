package com.pokedex.pokedex.theme;

import org.springframework.shell.style.StyleSettings;

public class PokedexThemeStyle extends StyleSettings {

    @Override
    public String listLevelError() {
        return "bold,fg:red";
    }

    @Override
    public String listLevelWarn() {
        return "fg:yellow";
    }

    @Override
    public String listLevelInfo() {
        return "fg:cyan";
    }

    @Override
    public String highlight() {
        return "underline,fg:bright-white";
    }

    @Override
    public String title() {
        return "bold,fg:bright-white";
    }

    @Override
    public String background() {
        return "bg:black,fg:white";
    }
}
