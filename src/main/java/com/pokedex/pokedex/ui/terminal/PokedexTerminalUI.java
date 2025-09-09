package com.pokedex.pokedex.ui.terminal;

import com.pokedex.pokedex.controller.PokedexController;
import com.pokedex.pokedex.dto.PokedexDTO;
import com.pokedex.pokedex.ui.gridview.PokedexGridView;
import com.pokedex.pokedex.ui.pokedexlistview.PokedexListView;
import org.springframework.shell.component.view.TerminalUI;
import org.springframework.shell.component.view.TerminalUIBuilder;
import org.springframework.shell.component.view.control.ListView;
import org.springframework.shell.component.view.event.EventLoop;
import org.springframework.shell.component.view.event.KeyEvent;
import org.springframework.shell.style.ThemeResolver;

import java.io.IOException;
import java.util.List;


public class PokedexTerminalUI {

    private final EventLoop eventLoop;
    private final TerminalUI terminalUI;
    private final PokedexController controller;
    private List<PokedexDTO> pokemons;
    private PokedexDTO dto;
    private PokedexListView listView;

    private static final int AMOUNT_OF_POKEMON = 151;

    public PokedexTerminalUI(TerminalUIBuilder builder, ThemeResolver themeResolver) throws IOException {

        this.terminalUI = builder.build();
        this.eventLoop = terminalUI.getEventLoop();
        this.controller = new PokedexController();

        this.pokemons = controller.getPokedex(AMOUNT_OF_POKEMON);

        this.listView = new PokedexListView(pokemons, themeResolver);

        subscribe();

        this.terminalUI.configure(listView);

        this.terminalUI.setRoot(listView, true);
        this.terminalUI.setFocus(listView);
        this.terminalUI.run();

    }

    public void run() {
        this.terminalUI.run();
    }

    private void subscribe() {

        eventLoop.viewEvents(ListView.ListViewOpenSelectedItemEvent.class).doOnNext(
                e -> {
                    ListView.ListViewItemEventArgs<PokedexDTO> arg = e.args();
                    this.dto = arg.item();

                    PokedexGridView gridView = new PokedexGridView(arg.item());

                    terminalUI.setRoot(gridView, true);
                    terminalUI.setFocus(gridView);
                }
        ).subscribe();

        eventLoop.keyEvents().doOnNext(
                (KeyEvent e) -> {
                    if (e.key() == 113) {
                        // instance q
                        terminalUI.setRoot(listView, true);
                        terminalUI.setFocus(listView);
                    } else if (e.key() == 110) {
                        // case n
                        if (dto.id() > pokemons.size() - 1) return;
                        System.out.println(dto.id());
                        PokedexDTO nextPokemonDTO = pokemons.get(dto.id());
                        this.dto = nextPokemonDTO;
                        PokedexGridView gridView = new PokedexGridView(nextPokemonDTO);
                        terminalUI.setRoot(gridView, true);
                        terminalUI.setFocus(gridView);
                    } else if (e.key() == 112) {
                        // case p
                        if (dto.id()-2 < 0) return;
                        PokedexDTO prevPokemonDTO = pokemons.get(dto.id()-2);
                        this.dto = prevPokemonDTO;
                        PokedexGridView gridView = new PokedexGridView(prevPokemonDTO);
                        terminalUI.setRoot(gridView, true);
                        terminalUI.setFocus(gridView);
                    }
                }
        ).subscribe();
    }

}
