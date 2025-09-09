package com.pokedex.pokedex.ui.gridview.griditems;

import com.pokedex.pokedex.dto.PokedexDTO;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.component.view.screen.Screen;

import java.util.List;


public class PokeGridAbilityBox extends PokedexGridBox {

    public PokeGridAbilityBox(PokedexDTO dto) {
        super(dto);
    }

    @Override
    protected void drawInternal(Screen screen) {
        super.drawInternal(screen);
        Screen.Writer writer = screen.writerBuilder().build();

        int y = getInnerRect().y();
        int x = getInnerRect().x()+2;

        List<String> abilities = dto.abilities();
        writer.text("Abilities:", x, y++);
        for (int i = 0; i < abilities.size(); i++) {
            writer.text("    Ability " + (i+1) + ": " + abilities.get(i), x, y);
            y++;
        }
    }
}
