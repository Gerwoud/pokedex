package com.pokedex.pokedex.commands;

import org.jline.utils.AttributedString;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.style.StyleSettings;
import org.springframework.shell.style.ThemeResolver;

@ShellComponent
public class ThemeCheck {

    private final ThemeResolver themeResolver;

    public ThemeCheck(ThemeResolver themeResolver) {
        this.themeResolver = themeResolver;
    }

    @ShellMethod(key = "print-theme")
    public void printTheme() {
        System.out.println("Active theme(s): " + themeResolver.themeNames());
        System.out.println("Theme style mappings:");

        for (String tag : StyleSettings.tags()) {
            var style = themeResolver.resolveStyleTag(tag);
            var sample = new AttributedString(" Sample (" + tag + ")", themeResolver.resolveStyle(style));
            System.out.println("  " + tag + " -> " + sample.toAnsi());
        }
    }
}
