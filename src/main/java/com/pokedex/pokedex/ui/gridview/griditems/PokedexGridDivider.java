package com.pokedex.pokedex.ui.gridview.griditems;

import org.springframework.shell.component.view.control.BoxView;
import org.springframework.shell.component.view.screen.Screen;
import org.springframework.shell.geom.HorizontalAlign;
import org.springframework.shell.geom.VerticalAlign;

public class PokedexGridDivider extends BoxView {


    @Override
    protected void drawInternal(Screen screen) {
        super.drawInternal(screen);
        Screen.Writer writer = screen.writerBuilder().build();
        writer.text("─".repeat(getRect().width()), getInnerRect(), HorizontalAlign.CENTER, VerticalAlign.BOTTOM);
    }
}
