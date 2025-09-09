package com.pokedex.pokedex.ui.terminal;

import com.pokedex.pokedex.controller.PokedexController;
import com.pokedex.pokedex.dto.PokedexDTO;
import com.pokedex.pokedex.ui.gridview.PokedexGridView;
import com.pokedex.pokedex.ui.pokedexlistview.PokedexListView;
import com.pokedex.pokedex.ui.pokedexlistview.PokedexListViewInfo;
import org.springframework.shell.component.view.TerminalUI;
import org.springframework.shell.component.view.TerminalUIBuilder;
import org.springframework.shell.component.view.control.GridView;
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
    private GridView gridView;

    private static final int AMOUNT_OF_POKEMON = 151;

    public PokedexTerminalUI(TerminalUIBuilder builder, ThemeResolver themeResolver) throws IOException {

        this.terminalUI = builder.build();
        this.eventLoop = terminalUI.getEventLoop();
        this.controller = new PokedexController();

        this.pokemons = controller.getPokedex(AMOUNT_OF_POKEMON);

        this.listView = new PokedexListView(pokemons, themeResolver);

        gridView = new GridView();
        gridView.setRowSize(listView.getRect().height());
        gridView.setColumnSize(-4,-1);

        gridView.addItem(listView,0,0,1,1,0,0);

        PokedexListViewInfo pokedexListViewInfo = new PokedexListViewInfo();
        gridView.addItem(pokedexListViewInfo,0,1,1,1,0,0);


        subscribe();

        this.terminalUI.configure(listView);

        this.terminalUI.setRoot(gridView, true);
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

                    PokedexGridView pokedexGridView = new PokedexGridView(arg.item());

                    terminalUI.setRoot(pokedexGridView, true);
                    terminalUI.setFocus(pokedexGridView);
                }
        ).subscribe();

        eventLoop.keyEvents().doOnNext(
                (KeyEvent e) -> {
                    if (e.key() == 113) {
                        // instance q
                        terminalUI.setRoot(gridView, true);
                        terminalUI.setFocus(listView);
                    } else if (e.key() == 110) {
                        // case n
                        if (dto.id() > pokemons.size() - 1) return;
                        System.out.println(dto.id());
                        PokedexDTO nextPokemonDTO = pokemons.get(dto.id());
                        this.dto = nextPokemonDTO;
                        PokedexGridView pokedexGridView = new PokedexGridView(nextPokemonDTO);
                        terminalUI.setRoot(pokedexGridView, true);
                        terminalUI.setFocus(pokedexGridView);
                    } else if (e.key() == 112) {
                        // case p
                        if (dto.id()-2 < 0) return;
                        PokedexDTO prevPokemonDTO = pokemons.get(dto.id()-2);
                        this.dto = prevPokemonDTO;
                        PokedexGridView pokedexGridView = new PokedexGridView(prevPokemonDTO);
                        terminalUI.setRoot(pokedexGridView, true);
                        terminalUI.setFocus(pokedexGridView);
                    }
                }
        ).subscribe();
    }

}
