package com.pokedex.pokedex.ui.gridview.griditems;

import com.pokedex.pokedex.dto.PokedexDTO;
import org.springframework.shell.component.view.screen.Screen;

public class PokedexGridDescriptionBox extends PokedexGridBox {

    public PokedexGridDescriptionBox(PokedexDTO dto) {
        super(dto);
    }

    @Override
    protected void drawInternal(Screen screen) {
        super.drawInternal(screen);

        Screen.Writer writer = screen.writerBuilder().build();

        int x = getInnerRect().x()+2;
        int y = getInnerRect().y();

        writer.text("Description:", x, y++);
        for (String line: dto.flavortext().split("[\n\u000c]")) {
            writer.text("   "+line, x, y++);
        }
    }
}
