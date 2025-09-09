package com.pokedex.pokedex.ui.pokedexlistview;

import com.pokedex.pokedex.dto.PokedexDTO;
import org.springframework.shell.component.view.control.cell.AbstractListCell;
import org.springframework.shell.style.ThemeResolver;

public class PokedexListViewCell extends AbstractListCell<PokedexDTO> {

    private PokedexDTO item;

    public PokedexListViewCell(PokedexDTO item, ThemeResolver themeResolver) {
        super(item);
        this.item = item;
        setThemeResolver(themeResolver);
    }

    @Override
    protected String getText() {
        return item.id() + " " + item.name();
    }

    @Override
    protected String getThemeName() {
        return "pokedex";
    }
}