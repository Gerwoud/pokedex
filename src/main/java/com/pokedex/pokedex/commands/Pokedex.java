package com.pokedex.pokedex.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pokedex.pokedex.controller.PokedexController;
import com.pokedex.pokedex.ui.terminal.PokedexTerminalUI;
import jakarta.annotation.PostConstruct;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.component.view.TerminalUIBuilder;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.style.ThemeResolver;

import java.io.IOException;


@ShellComponent
public class Pokedex {

    @Autowired
    TerminalUIBuilder builder;

    @Autowired
    ThemeResolver themeResolver;

    private PokedexController controller;

    public Pokedex() {
    }

    @PostConstruct
    public void init() {
        this.controller = new PokedexController();
    }


    @ShellMethod(key = "pokedex")
    public String pokedex(@ShellOption(value = {"--name"}, defaultValue = "bulbarsaur") String name) {
        try {
            return controller.getPokemon(name).toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @ShellMethod
    public String next(@ShellOption(value = {"--amount"}, defaultValue = "1") int amount) {
        try {
            return controller.nextPokemon(amount).toString();
        } catch (JsonProcessingException e) {
            return "Error processing JSON: " + e.getMessage();
        }
    }

    @ShellMethod
    public String previous(@ShellOption(value = {"--amount"}, defaultValue = "1") int amount) {
        try {
            return controller.nextPokemon(-amount).toString();
        } catch (JsonProcessingException e) {
            return "Error processing JSON: " + e.getMessage();
        }
    }

    @ShellMethod
    public void list() throws IOException {
        PokedexTerminalUI pokedexTerminalUI = new PokedexTerminalUI(builder, themeResolver);
        pokedexTerminalUI.run();
    }
}

