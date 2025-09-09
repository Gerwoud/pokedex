package com.pokedex.pokedex.theme;

import org.springframework.shell.style.FigureSettings;
import org.springframework.shell.style.StyleSettings;
import org.springframework.shell.style.ThemeSettings;

public class PokedexTheme extends ThemeSettings {

    @Override
    public StyleSettings styles() {
        return new PokedexThemeStyle();
    }

    @Override
    public FigureSettings figures() {
        return new PokedexThemeFigure();
    }

}
