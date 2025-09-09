package com.pokedex.pokedex.ui.gridview.griditems;

import com.pokedex.pokedex.dto.PokedexDTO;
import org.springframework.shell.component.view.control.BoxView;
import org.springframework.shell.component.view.screen.Screen;
import org.springframework.shell.geom.HorizontalAlign;
import org.springframework.shell.geom.VerticalAlign;

public class PokedexGridNavigationBox extends PokedexGridBox {

    public PokedexGridNavigationBox(PokedexDTO dto) {
        super(dto);
    }

    @Override
    protected void drawInternal(Screen screen) {
        super.drawInternal(screen);

        int w = getInnerRect().width();

        Screen.Writer writer = screen.writerBuilder().build();
        boolean hasPrev = dto.prevPokemon() != null && !dto.prevPokemon().isEmpty();
        boolean hasNext = dto.nextPokemon() != null && !dto.nextPokemon().isEmpty();

        StringBuilder output = new StringBuilder();
        if (hasPrev) {
            output.append("<- (p) ").append(dto.prevPokemon());
        }
        while (output.length() < w - (hasNext ? dto.nextPokemon().length() + 7 : 0)) {
            output.append(" ");
        }
        if (hasNext) {
            output.append(dto.nextPokemon()).append(" (n) ->");
        }

        writer.text(output.toString(), getInnerRect(), HorizontalAlign.CENTER, VerticalAlign.TOP);
        writer.text("(q) quit", getInnerRect(), HorizontalAlign.CENTER, VerticalAlign.TOP);
    }
}
