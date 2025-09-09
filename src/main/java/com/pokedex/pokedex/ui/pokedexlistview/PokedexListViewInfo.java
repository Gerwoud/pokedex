package com.pokedex.pokedex.ui.pokedexlistview;

import org.springframework.shell.component.view.control.BoxView;
import org.springframework.shell.component.view.screen.Screen;
import org.springframework.shell.geom.Rectangle;

public class PokedexListViewInfo extends BoxView {

    public PokedexListViewInfo() {
        this.setShowBorder(true);
    }

    @Override
    protected void drawInternal(Screen screen) {
        super.drawInternal(screen);
        Screen.Writer writer = screen.writerBuilder().build();
        Rectangle rect = getInnerRect();
        writer.text("Navigation:", rect.x()+1, rect.y());
        writer.text("  Arrow up", rect.x()+1, rect.y()+1);
        writer.text("  Arrow down", rect.x()+1, rect.y()+2);
        writer.text("  Enter", rect.x()+1, rect.y()+3);
    }
}
