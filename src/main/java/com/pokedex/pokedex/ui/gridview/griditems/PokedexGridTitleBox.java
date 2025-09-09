package com.pokedex.pokedex.ui.gridview.griditems;

import com.pokedex.pokedex.dto.PokedexDTO;
import org.springframework.shell.component.view.screen.Screen;
import org.springframework.shell.geom.HorizontalAlign;
import org.springframework.shell.geom.Rectangle;
import org.springframework.shell.geom.VerticalAlign;

public class PokedexGridTitleBox extends PokedexGridBox {

    public PokedexGridTitleBox(PokedexDTO dto) {
        super(dto);
        setShowBorder(true);
    }

    @Override
    protected void drawInternal(Screen screen) {
        super.drawInternal(screen);
        Screen.Writer writer = screen.writerBuilder().build();
        Rectangle rect = getInnerRect();
        writer.text("# " + dto.id() + " " + dto.name(), rect, HorizontalAlign.CENTER, VerticalAlign.CENTER);
    }
}
