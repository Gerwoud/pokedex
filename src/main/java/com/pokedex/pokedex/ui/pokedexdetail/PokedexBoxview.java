package com.pokedex.pokedex.ui.pokedexdetail;

import com.pokedex.pokedex.dto.PokedexDTO;
import org.springframework.shell.component.view.control.BoxView;
import org.springframework.shell.component.view.screen.Screen;
import org.springframework.shell.geom.Rectangle;

public class PokedexBoxview extends BoxView {

    PokedexDTO pokemonDTO;

    public PokedexBoxview(PokedexDTO item) {
        super();
        pokemonDTO = item;

        this.setShowBorder(true);
        this.setTitle(" " + pokemonDTO.name() + " ");
    }

    @Override
    protected void drawInternal(Screen screen) {
        Screen.Writer writer = screen.writerBuilder().build();
        Rectangle rect = getInnerRect();

        pokemonDTO.draw(writer, rect);
        super.drawInternal(screen);

    }
}
